#!/usr/bin/env node
/**
 * Fetches a merged PR's details (title, body, files changed) and appends
 * a "Recently merged" entry to the Confluence project summary page.
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

const auth = Buffer.from(`${CONFLUENCE_EMAIL}:${CONFLUENCE_API_TOKEN}`).toString('base64');
const confluenceHeaders = {
  Accept: 'application/json',
  'Content-Type': 'application/json',
  Authorization: `Basic ${auth}`,
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

async function fetchPrDetails() {
  const url = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}`;
  const res = await fetch(url, { headers: ghHeaders });
  if (!res.ok) {
    throw new Error(`GitHub API error: ${res.status} ${await res.text()}`);
  }
  const pr = await res.json();
  const filesUrl = `https://api.github.com/repos/${REPO_OWNER}/${REPO_NAME}/pulls/${PR_NUMBER}/files`;
  const filesRes = await fetch(filesUrl, { headers: ghHeaders });
  const files = filesRes.ok ? await filesRes.json() : [];
  const fileNames = files.map((f) => f.filename);
  const mergedAt = pr.merged_at ? new Date(pr.merged_at).toISOString().slice(0, 10) : '';
  return {
    title: pr.title,
    body: pr.body || '',
    htmlUrl: pr.html_url,
    mergedAt,
    fileNames,
  };
}

function buildConfluenceStorageFragment(pr) {
  const titleEsc = escapeHtml(pr.title);
  const link = `<a href="${escapeHtml(pr.htmlUrl)}">#${PR_NUMBER} ${titleEsc}</a>`;
  const dateLine = pr.mergedAt ? ` <span>(${pr.mergedAt})</span>` : '';
  let html = `<p><strong>Merged PR ${link}</strong>${dateLine}</p>`;
  const desc = (pr.body || '').trim().slice(0, 500);
  if (desc) {
    const descEsc = escapeHtml(desc).replace(/\n/g, ' ');
    html += `<p>${descEsc}</p>`;
  }
  if (pr.fileNames && pr.fileNames.length > 0) {
    html += '<h3>Files changed</h3><ul>';
    const maxFiles = 30;
    const list = pr.fileNames.slice(0, maxFiles);
    for (const f of list) {
      html += `<li>${escapeHtml(f)}</li>`;
    }
    if (pr.fileNames.length > maxFiles) {
      html += `<li><em>... and ${pr.fileNames.length - maxFiles} more</em></li>`;
    }
    html += '</ul>';
  }
  return html;
}

async function getConfluencePage() {
  const url = `${CONFLUENCE_BASE_URL}/wiki/api/v2/pages/${CONFLUENCE_PAGE_ID}?body-format=storage`;
  const res = await fetch(url, { headers: confluenceHeaders });
  if (!res.ok) {
    throw new Error(`Confluence GET error: ${res.status} ${await res.text()}`);
  }
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
  const version = data.version?.number ?? 1;
  const title = data.title || 'Project Summary';
  const status = data.status || 'current';
  return { currentStorage, version, title, status };
}

async function updateConfluencePage(newBodyStorage, version, title, status, versionMessage) {
  const url = `${CONFLUENCE_BASE_URL}/wiki/api/v2/pages/${CONFLUENCE_PAGE_ID}`;
  const payload = {
    id: CONFLUENCE_PAGE_ID,
    status,
    title,
    body: {
      representation: 'storage',
      value: newBodyStorage,
    },
    version: {
      number: version + 1,
      message: versionMessage || `Sync from GitHub PR #${PR_NUMBER}`,
    },
  };
  const res = await fetch(url, {
    method: 'PUT',
    headers: confluenceHeaders,
    body: JSON.stringify(payload),
  });
  if (!res.ok) {
    throw new Error(`Confluence PUT error: ${res.status} ${await res.text()}`);
  }
  return res.json();
}

async function main() {
  console.log('Fetching PR details...');
  const pr = await fetchPrDetails();
  console.log(`PR: ${pr.title} (${pr.fileNames.length} files)`);

  const fragment = buildConfluenceStorageFragment(pr);

  console.log('Fetching current Confluence page...');
  const { currentStorage, version, title, status } = await getConfluencePage();

  const sectionMarker = '<h2>Recently merged</h2>';
  const insertBlock = sectionMarker + fragment;
  let newStorage;
  if (currentStorage.includes(sectionMarker)) {
    const idx = currentStorage.indexOf(sectionMarker);
    const afterHeading = currentStorage.indexOf('</h2>', idx) + 5;
    newStorage = currentStorage.slice(0, afterHeading) + fragment + currentStorage.slice(afterHeading);
  } else {
    newStorage = currentStorage + '\n\n' + insertBlock;
  }

  console.log('Updating Confluence page...');
  await updateConfluencePage(newStorage, version, title, status, `GitHub PR #${PR_NUMBER}: ${pr.title}`);
  console.log('Done. Confluence project summary updated.');
}

main().catch((err) => {
  console.error(err);
  process.exit(1);
});
