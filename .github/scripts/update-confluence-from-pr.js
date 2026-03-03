#!/usr/bin/env node
/**
 * On PR merge: fetches the code diff, parses it to generate a structured
 * summary of what changed, then updates the Confluence project summary page.
 *
 * No external AI API needed — diff is parsed locally.
 *
 * Required env:
 *   GITHUB_TOKEN, PR_NUMBER, REPO_OWNER, REPO_NAME
 *   CONFLUENCE_BASE_URL, CONFLUENCE_PAGE_ID, CONFLUENCE_EMAIL, CONFLUENCE_API_TOKEN
 */

const CONFLUENCE_PAGE_ID = process.env.CONFLUENCE_PAGE_ID;
const CONFLUENCE_BASE_URL = (process.env.CONFLUENCE_BASE_URL || '').replace(/\/$/, '');
const CONFLUENCE_EMAIL = process.env.CONFLUENCE_EMAIL;
const CONFLUENCE_API_TOKEN = process.env.CONFLUENCE_API_TOKEN;
const GITHUB_TOKEN = process.env.GITHUB_TOKEN;
const PR_NUMBER = process.env.PR_NUMBER;
const REPO_OWNER = process.env.REPO_OWNER;
const REPO_NAME = process.env.REPO_NAME;

function requireEnv(name) {
  const v = process.env[name];
  if (!v) {
    console.error(`Missing required env: ${name}`);
    process.exit(1);
  }
  return v;
}

requireEnv('CONFLUENCE_PAGE_ID');
requireEnv('CONFLUENCE_BASE_URL');
requireEnv('CONFLUENCE_EMAIL');
requireEnv('CONFLUENCE_API_TOKEN');
requireEnv('GITHUB_TOKEN');
requireEnv('PR_NUMBER');
requireEnv('REPO_OWNER');
requireEnv('REPO_NAME');

const confluenceAuth = Buffer.from(`${CONFLUENCE_EMAIL}:${CONFLUENCE_API_TOKEN}`).toString('base64');
const confluenceHeaders = {
  Accept: 'application/json',
  'Content-Type': 'application/json',
  Authorization: `Basic ${confluenceAuth}`,
};

const ghHeaders = {
  Accept: 'application/vnd.github+json',
  Authorization: `Bearer ${GITHUB_TOKEN}`,
  'X-GitHub-Api-Version': '2022-11-28',
};

function escapeHtml(s) {
  if (!s || typeof s !== 'string') return '';
  return s
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;');
}

// ── GitHub ──

async function fetchPrDetails() {
  const url = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}`;
  const res = await fetch(url, { headers: ghHeaders });
  if (!res.ok) throw new Error(`GitHub PR API error: ${res.status} ${await res.text()}`);
  const pr = await res.json();
  const mergedAt = pr.merged_at ? new Date(pr.merged_at).toISOString().slice(0, 10) : '';
  return { title: pr.title, body: pr.body || '', htmlUrl: pr.html_url, mergedAt };
}

async function fetchPrDiff() {
  const url = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}`;
  const res = await fetch(url, {
    headers: { ...ghHeaders, Accept: 'application/vnd.github.v3.diff' },
  });
  if (!res.ok) throw new Error(`GitHub diff API error: ${res.status} ${await res.text()}`);
  return res.text();
}

async function fetchPrFiles() {
  const url = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}/files?per_page=100`;
  const res = await fetch(url, { headers: ghHeaders });
  if (!res.ok) return [];
  return res.json();
}

// ── Diff parser ──

const SIGNATURE_PATTERNS = [
  /^\+\s*(?:public|private|protected|static|final|abstract|override)?\s*(?:fun|def|function|class|interface|object|enum|struct|data class|sealed class)\s+\w+/i,
  /^\+\s*(?:public|private|protected|static|final|abstract|synchronized)?\s*(?:void|int|long|String|boolean|Boolean|List|Map|Set|Optional|[\w<>\[\]]+)\s+\w+\s*\(/,
  /^\+\s*(?:export\s+)?(?:async\s+)?(?:function|class|const|let|var)\s+\w+/,
  /^\+\s*(?:def|class)\s+\w+/,
  /^\+\s*(?:func|type|struct|interface)\s+\w+/,
];

function extractSignature(line) {
  const cleaned = line.replace(/^\+\s*/, '').trim();
  const parenIdx = cleaned.indexOf('{');
  const sig = parenIdx > 0 ? cleaned.slice(0, parenIdx).trim() : cleaned;
  return sig.length > 120 ? sig.slice(0, 117) + '...' : sig;
}

function parseDiff(diff) {
  const files = [];
  const fileSections = diff.split(/^diff --git /m).filter(Boolean);

  for (const section of fileSections) {
    const lines = section.split('\n');
    const headerMatch = lines[0]?.match(/a\/(.+?) b\/(.+)/);
    if (!headerMatch) continue;

    const filename = headerMatch[2];
    const added = [];
    const removed = [];
    const signatures = [];

    for (const line of lines) {
      if (line.startsWith('+') && !line.startsWith('+++')) {
        added.push(line.slice(1));
        for (const pat of SIGNATURE_PATTERNS) {
          if (pat.test(line)) {
            signatures.push(extractSignature(line));
            break;
          }
        }
      } else if (line.startsWith('-') && !line.startsWith('---')) {
        removed.push(line.slice(1));
      }
    }

    files.push({
      filename,
      addedLines: added.length,
      removedLines: removed.length,
      signatures: [...new Set(signatures)],
      sampleAdded: added.filter((l) => l.trim().length > 3).slice(0, 5),
      sampleRemoved: removed.filter((l) => l.trim().length > 3).slice(0, 3),
    });
  }

  return files;
}

function categorizeFile(filename) {
  const lower = filename.toLowerCase();
  if (lower.includes('test')) return 'Tests';
  if (lower.includes('build.gradle') || lower.includes('pom.xml') || lower.includes('package.json') || lower.includes('.yml') || lower.includes('.yaml')) return 'Build / Config';
  if (lower.includes('readme') || lower.includes('.md')) return 'Documentation';
  if (lower.endsWith('.xml') && (lower.includes('layout') || lower.includes('drawable') || lower.includes('values'))) return 'UI / Resources';
  return 'Source Code';
}

function generateSummary(pr, diffFiles, ghFiles) {
  const totalAdded = ghFiles.reduce((a, f) => a + (f.additions || 0), 0);
  const totalRemoved = ghFiles.reduce((a, f) => a + (f.deletions || 0), 0);

  const categories = {};
  for (const f of diffFiles) {
    const cat = categorizeFile(f.filename);
    if (!categories[cat]) categories[cat] = [];
    categories[cat].push(f);
  }

  let html = '';

  // High-level summary from PR body
  const desc = (pr.body || '').trim();
  if (desc) {
    const shortDesc = desc.slice(0, 600);
    html += `<p>${escapeHtml(shortDesc).replace(/\n/g, '<br/>')}</p>`;
  }

  // Stats
  html += `<p><strong>${ghFiles.length} files changed</strong> — `;
  html += `<span style="color:green">+${totalAdded} additions</span>, `;
  html += `<span style="color:red">-${totalRemoved} deletions</span></p>`;

  // Key changes by category
  html += '<h3>Key Changes</h3>';

  for (const [cat, files] of Object.entries(categories)) {
    html += `<p><strong>${escapeHtml(cat)}</strong></p><ul>`;

    for (const f of files) {
      const shortName = f.filename.split('/').slice(-2).join('/');
      let bullet = `<strong>${escapeHtml(shortName)}</strong>`;
      bullet += ` (+${f.addedLines} -${f.removedLines})`;

      if (f.signatures.length > 0) {
        const sigs = f.signatures.slice(0, 4).map((s) => `<code>${escapeHtml(s)}</code>`).join(', ');
        bullet += `<br/>New/modified: ${sigs}`;
      }

      const ghFile = ghFiles.find((g) => g.filename === f.filename);
      if (ghFile) {
        const statusLabel = { added: 'New file', removed: 'Deleted', renamed: 'Renamed', modified: 'Modified' };
        const label = statusLabel[ghFile.status] || ghFile.status;
        if (ghFile.status !== 'modified') {
          bullet += ` — <em>${label}</em>`;
        }
      }

      html += `<li>${bullet}</li>`;
    }
    html += '</ul>';
  }

  return html;
}

// ── Confluence ──

async function getConfluencePage() {
  const url = `${CONFLUENCE_BASE_URL}/wiki/api/v2/pages/${CONFLUENCE_PAGE_ID}?body-format=storage`;
  const res = await fetch(url, { headers: confluenceHeaders });
  if (!res.ok) throw new Error(`Confluence GET error: ${res.status} ${await res.text()}`);
  const data = await res.json();
  const body = data.body;
  let currentStorage = '';
  if (body) {
    if (typeof body.storage === 'object' && body.storage != null && 'value' in body.storage) {
      currentStorage = body.storage.value || '';
    } else if (typeof body.value === 'string') {
      currentStorage = body.value;
    }
  }
  return {
    currentStorage,
    version: data.version?.number ?? 1,
    title: data.title || 'Project Summary',
    status: data.status || 'current',
  };
}

async function updateConfluencePage(newBody, version, title, status, versionMessage) {
  const url = `${CONFLUENCE_BASE_URL}/wiki/api/v2/pages/${CONFLUENCE_PAGE_ID}`;
  const payload = {
    id: CONFLUENCE_PAGE_ID,
    status,
    title,
    body: { representation: 'storage', value: newBody },
    version: { number: version + 1, message: versionMessage },
  };
  const res = await fetch(url, {
    method: 'PUT',
    headers: confluenceHeaders,
    body: JSON.stringify(payload),
  });
  if (!res.ok) throw new Error(`Confluence PUT error: ${res.status} ${await res.text()}`);
  return res.json();
}

// ── Build HTML fragment ──

function buildFragment(pr, summary, ghFiles) {
  const titleEsc = escapeHtml(pr.title);
  const link = `<a href="${escapeHtml(pr.htmlUrl)}">#${PR_NUMBER} ${titleEsc}</a>`;
  const dateLine = pr.mergedAt ? ` <em>(${pr.mergedAt})</em>` : '';

  let html = `<h3>Merged PR ${link}${dateLine}</h3>`;
  html += summary;
  html += '<hr/>';
  return html;
}

// ── Main ──

async function main() {
  console.log('Fetching PR details...');
  const [pr, diff, ghFiles] = await Promise.all([
    fetchPrDetails(),
    fetchPrDiff(),
    fetchPrFiles(),
  ]);
  console.log(`PR: ${pr.title} | ${ghFiles.length} files | diff: ${diff.length} chars`);

  console.log('Parsing diff and generating summary...');
  const diffFiles = parseDiff(diff);
  const summary = generateSummary(pr, diffFiles, ghFiles);
  console.log(`Parsed ${diffFiles.length} files from diff.`);

  const fragment = buildFragment(pr, summary, ghFiles);

  console.log('Fetching current Confluence page...');
  const { currentStorage, version, title, status } = await getConfluencePage();

  const sectionMarker = '<h2>Recently merged</h2>';
  let newStorage;
  if (currentStorage.includes(sectionMarker)) {
    const idx = currentStorage.indexOf(sectionMarker);
    const afterHeading = currentStorage.indexOf('</h2>', idx) + 5;
    newStorage = currentStorage.slice(0, afterHeading) + '\n' + fragment + currentStorage.slice(afterHeading);
  } else {
    newStorage = currentStorage + '\n\n' + sectionMarker + '\n' + fragment;
  }

  console.log('Updating Confluence page...');
  await updateConfluencePage(newStorage, version, title, status, `GitHub PR #${PR_NUMBER}: ${pr.title}`);
  console.log('Done. Confluence project summary updated.');
}

main().catch((err) => {
  console.error(err);
  process.exit(1);
});
