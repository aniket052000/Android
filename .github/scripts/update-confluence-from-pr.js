// #!/usr/bin/env node
// /**
//  * Fetches a merged PR's details (title, body, files changed) and appends
//  * a "Recently merged" entry to the Confluence project summary page.
//  *
//  * Required env:
//  *   GITHUB_TOKEN, PR_NUMBER, REPO_OWNER, REPO_NAME
//  *   CONFLUENCE_BASE_URL, CONFLUENCE_PAGE_ID, CONFLUENCE_EMAIL, CONFLUENCE_API_TOKEN
//  */

// const CONFLUENCE_PAGE_ID = process.env.CONFLUENCE_PAGE_ID;
// const CONFLUENCE_BASE_URL = (process.env.CONFLUENCE_BASE_URL || '').replace(/\/$/, '');
// const CONFLUENCE_EMAIL = process.env.CONFLUENCE_EMAIL;
// const CONFLUENCE_API_TOKEN = process.env.CONFLUENCE_API_TOKEN;
// const GITHUB_TOKEN = process.env.GITHUB_TOKEN;
// const PR_NUMBER = process.env.PR_NUMBER;
// const REPO_OWNER = process.env.REPO_OWNER;
// const REPO_NAME = process.env.REPO_NAME;

// function requireEnv(name) {
//   const v = process.env[name];
//   if (!v) {
//     console.error(`Missing required env: ${name}`);
//     process.exit(1);
//   }
//   return v;
// }

// requireEnv('CONFLUENCE_PAGE_ID');
// requireEnv('CONFLUENCE_BASE_URL');
// requireEnv('CONFLUENCE_EMAIL');
// requireEnv('CONFLUENCE_API_TOKEN');
// requireEnv('GITHUB_TOKEN');
// requireEnv('PR_NUMBER');
// requireEnv('REPO_OWNER');
// requireEnv('REPO_NAME');

// const auth = Buffer.from(`${CONFLUENCE_EMAIL}:${CONFLUENCE_API_TOKEN}`).toString('base64');
// const confluenceHeaders = {
//   Accept: 'application/json',
//   'Content-Type': 'application/json',
//   Authorization: `Basic ${auth}`,
// };

// const ghHeaders = {
//   Accept: 'application/vnd.github+json',
//   Authorization: `Bearer ${GITHUB_TOKEN}`,
//   'X-GitHub-Api-Version': '2022-11-28',
// };

// function escapeHtml(s) {
//   if (!s || typeof s !== 'string') return '';
//   return s
//     .replace(/&/g, '&amp;')
//     .replace(/</g, '&lt;')
//     .replace(/>/g, '&gt;')
//     .replace(/"/g, '&quot;');
// }

// async function fetchPrDetails() {
//   const url = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}`;
//   const res = await fetch(url, { headers: ghHeaders });
//   if (!res.ok) {
//     throw new Error(`GitHub API error: ${res.status} ${await res.text()}`);
//   }
//   const pr = await res.json();
//   const filesUrl = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}/files`;
//   const filesRes = await fetch(filesUrl, { headers: ghHeaders });
//   const files = filesRes.ok ? await filesRes.json() : [];
//   const fileNames = files.map((f) => f.filename);
//   const mergedAt = pr.merged_at ? new Date(pr.merged_at).toISOString().slice(0, 10) : '';
//   return {
//     title: pr.title,
//     body: pr.body || '',
//     htmlUrl: pr.html_url,
//     mergedAt,
//     fileNames,
//   };
// }

// function buildConfluenceStorageFragment(pr) {
//   const titleEsc = escapeHtml(pr.title);
//   const link = `<a href="${escapeHtml(pr.htmlUrl)}">#${PR_NUMBER} ${titleEsc}</a>`;
//   const dateLine = pr.mergedAt ? ` <span>(${pr.mergedAt})</span>` : '';
//   let html = `<p><strong>Merged PR ${link}</strong>${dateLine}</p>`;
//   const desc = (pr.body || '').trim().slice(0, 500);
//   if (desc) {
//     const descEsc = escapeHtml(desc).replace(/\n/g, ' ');
//     html += `<p>${descEsc}</p>`;
//   }
//   if (pr.fileNames && pr.fileNames.length > 0) {
//     html += '<h3>Files changed</h3><ul>';
//     const maxFiles = 30;
//     const list = pr.fileNames.slice(0, maxFiles);
//     for (const f of list) {
//       html += `<li>${escapeHtml(f)}</li>`;
//     }
//     if (pr.fileNames.length > maxFiles) {
//       html += `<li><em>... and ${pr.fileNames.length - maxFiles} more</em></li>`;
//     }
//     html += '</ul>';
//   }
//   return html;
// }

// async function getConfluencePage() {
//   const url = `${CONFLUENCE_BASE_URL}/wiki/api/v2/pages/${CONFLUENCE_PAGE_ID}?body-format=storage`;
//   const res = await fetch(url, { headers: confluenceHeaders });
//   if (!res.ok) {
//     throw new Error(`Confluence GET error: ${res.status} ${await res.text()}`);
//   }
//   const data = await res.json();
//   const body = data.body;
//   let currentStorage = '';
//   if (body) {
//     if (typeof body.storage === 'object' && body.storage != null && 'value' in body.storage) {
//       currentStorage = body.storage.value || '';
//     } else if (typeof body.value === 'string') {
//       currentStorage = body.value;
//     }
//   }
//   const version = data.version?.number ?? 1;
//   const title = data.title || 'Project Summary';
//   const status = data.status || 'current';
//   return { currentStorage, version, title, status };
// }

// async function updateConfluencePage(newBodyStorage, version, title, status, versionMessage) {
//   const url = `${CONFLUENCE_BASE_URL}/wiki/api/v2/pages/${CONFLUENCE_PAGE_ID}`;
//   const payload = {
//     id: CONFLUENCE_PAGE_ID,
//     status,
//     title,
//     body: {
//       representation: 'storage',
//       value: newBodyStorage,
//     },
//     version: {
//       number: version + 1,
//       message: versionMessage || `Sync from GitHub PR #${PR_NUMBER}`,
//     },
//   };
//   const res = await fetch(url, {
//     method: 'PUT',
//     headers: confluenceHeaders,
//     body: JSON.stringify(payload),
//   });
//   if (!res.ok) {
//     throw new Error(`Confluence PUT error: ${res.status} ${await res.text()}`);
//   }
//   return res.json();
// }

// async function main() {
//   console.log('Fetching PR details...');
//   const pr = await fetchPrDetails();
//   console.log(`PR: ${pr.title} (${pr.fileNames.length} files)`);

//   const fragment = buildConfluenceStorageFragment(pr);

//   console.log('Fetching current Confluence page...');
//   const { currentStorage, version, title, status } = await getConfluencePage();

//   const sectionMarker = '<h2>Recently merged</h2>';
//   const insertBlock = sectionMarker + fragment;
//   let newStorage;
//   if (currentStorage.includes(sectionMarker)) {
//     const idx = currentStorage.indexOf(sectionMarker);
//     const afterHeading = currentStorage.indexOf('</h2>', idx) + 5;
//     newStorage = currentStorage.slice(0, afterHeading) + fragment + currentStorage.slice(afterHeading);
//   } else {
//     newStorage = currentStorage + '\n\n' + insertBlock;
//   }

//   console.log('Updating Confluence page...');
//   await updateConfluencePage(newStorage, version, title, status, `GitHub PR #${PR_NUMBER}: ${pr.title}`);
//   console.log('Done. Confluence project summary updated.');
// }

// main().catch((err) => {
//   console.error(err);
//   process.exit(1);
// });



#!/usr/bin/env node
/**
 * On PR merge: fetches the code diff, uses OpenAI to generate an intelligent
 * summary of what changed, then updates the Confluence project summary page.
 *
 * Required env:
 *   GITHUB_TOKEN, PR_NUMBER, REPO_OWNER, REPO_NAME
 *   CONFLUENCE_BASE_URL, CONFLUENCE_PAGE_ID, CONFLUENCE_EMAIL, CONFLUENCE_API_TOKEN
 *   OPENAI_API_KEY
 */

const CONFLUENCE_PAGE_ID = process.env.CONFLUENCE_PAGE_ID;
const CONFLUENCE_BASE_URL = (process.env.CONFLUENCE_BASE_URL || '').replace(/\/$/, '');
const CONFLUENCE_EMAIL = process.env.CONFLUENCE_EMAIL;
const CONFLUENCE_API_TOKEN = process.env.CONFLUENCE_API_TOKEN;
const GITHUB_TOKEN = process.env.GITHUB_TOKEN;
const PR_NUMBER = process.env.PR_NUMBER;
const REPO_OWNER = process.env.REPO_OWNER;
const REPO_NAME = process.env.REPO_NAME;
const OPENAI_API_KEY = process.env.OPENAI_API_KEY;

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
requireEnv('OPENAI_API_KEY');

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

// ── GitHub: PR metadata ──

async function fetchPrDetails() {
  const url = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}`;
  const res = await fetch(url, { headers: ghHeaders });
  if (!res.ok) throw new Error(`GitHub PR API error: ${res.status} ${await res.text()}`);
  const pr = await res.json();
  const mergedAt = pr.merged_at ? new Date(pr.merged_at).toISOString().slice(0, 10) : '';
  return {
    title: pr.title,
    body: pr.body || '',
    htmlUrl: pr.html_url,
    mergedAt,
  };
}

// ── GitHub: full code diff ──

async function fetchPrDiff() {
  const url = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}`;
  const res = await fetch(url, {
    headers: {
      ...ghHeaders,
      Accept: 'application/vnd.github.v3.diff',
    },
  });
  if (!res.ok) throw new Error(`GitHub diff API error: ${res.status} ${await res.text()}`);
  return res.text();
}

// ── GitHub: files changed (for fallback / file list) ──

async function fetchPrFiles() {
  const url = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}/files?per_page=100`;
  const res = await fetch(url, { headers: ghHeaders });
  if (!res.ok) return [];
  const files = await res.json();
  return files.map((f) => ({
    filename: f.filename,
    status: f.status,
    additions: f.additions,
    deletions: f.deletions,
  }));
}

// ── OpenAI: summarize code changes ──

const MAX_DIFF_CHARS = 60000;

async function summarizeWithAI(diff, prTitle, prBody, files) {
  const truncatedDiff = diff.length > MAX_DIFF_CHARS
    ? diff.slice(0, MAX_DIFF_CHARS) + '\n\n... [diff truncated] ...'
    : diff;

  const fileList = files.map((f) => `${f.status} ${f.filename} (+${f.additions} -${f.deletions})`).join('\n');

  const prompt = `You are a senior software engineer. A pull request was merged. Based on the code diff below, write a concise project summary update suitable for a Confluence project documentation page.

**PR Title:** ${prTitle}
**PR Description:** ${prBody || '(none)'}

**Files changed:**
${fileList}

**Code diff:**
\`\`\`
${truncatedDiff}
\`\`\`

Write the summary as HTML that will be inserted into a Confluence page. Include:
1. A one-paragraph high-level summary of what this PR does and why (in plain language, not code-speak).
2. A "Key Changes" section as a bullet list of the most important changes (max 8 bullets), each explaining what was changed and its impact.
3. If there are architecture/design changes, mention them.
4. Skip trivial changes like import reordering or formatting.

Use these HTML tags only: <p>, <strong>, <em>, <ul>, <li>, <h3>.
Do NOT include a top-level heading (the caller adds that). Do NOT wrap in \`\`\` code blocks.
Be concise — this is a project summary, not a code review.`;

  const res = await fetch('https://api.openai.com/v1/chat/completions', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${OPENAI_API_KEY}`,
    },
    body: JSON.stringify({
      model: 'gpt-4o',
      messages: [{ role: 'user', content: prompt }],
      max_tokens: 1500,
      temperature: 0.3,
    }),
  });

  if (!res.ok) {
    const errText = await res.text();
    console.error(`OpenAI API error: ${res.status} ${errText}`);
    return null;
  }

  const data = await res.json();
  let content = data.choices?.[0]?.message?.content || '';
  content = content.replace(/```html\s*/gi, '').replace(/```\s*/g, '').trim();
  return content;
}

// ── Confluence: read / write page ──

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

function buildFragment(pr, aiSummary, files) {
  const titleEsc = escapeHtml(pr.title);
  const link = `<a href="${escapeHtml(pr.htmlUrl)}">#${PR_NUMBER} ${titleEsc}</a>`;
  const dateLine = pr.mergedAt ? ` <em>(${pr.mergedAt})</em>` : '';

  let html = `<h3>Merged PR ${link}${dateLine}</h3>`;

  if (aiSummary) {
    html += aiSummary;
  } else {
    const desc = (pr.body || '').trim().slice(0, 500);
    if (desc) html += `<p>${escapeHtml(desc).replace(/\n/g, ' ')}</p>`;
  }

  if (files && files.length > 0) {
    const total = files.reduce((a, f) => a + f.additions + f.deletions, 0);
    html += `<p><em>${files.length} files changed (${total} lines)</em></p>`;
  }

  html += '<hr/>';
  return html;
}

// ── Main ──

async function main() {
  console.log('Fetching PR details...');
  const [pr, diff, files] = await Promise.all([
    fetchPrDetails(),
    fetchPrDiff(),
    fetchPrFiles(),
  ]);
  console.log(`PR: ${pr.title} | ${files.length} files | diff: ${diff.length} chars`);

  console.log('Sending diff to OpenAI for summarization...');
  const aiSummary = await summarizeWithAI(diff, pr.title, pr.body, files);
  if (aiSummary) {
    console.log('AI summary generated successfully.');
  } else {
    console.log('AI summary failed, falling back to PR description.');
  }

  const fragment = buildFragment(pr, aiSummary, files);

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
  console.log('Done. Confluence project summary updated with AI-generated summary.');
}

main().catch((err) => {
  console.error(err);
  process.exit(1);
});

