#!/usr/bin/env node
/**
 * On PR merge:
 *   1. Reads the existing Confluence project summary page
 *   2. Fetches the PR code diff
 *   3. Sends both to Groq AI (Llama 3.3 70B, free tier)
 *   4. AI regenerates the entire project summary incorporating the new changes
 *   5. Replaces the Confluence page content with the updated summary
 *
 * Required env:
 *   GITHUB_TOKEN, PR_NUMBER, REPO_OWNER, REPO_NAME
 *   CONFLUENCE_BASE_URL, CONFLUENCE_PAGE_ID, CONFLUENCE_EMAIL, CONFLUENCE_API_TOKEN
 *   GROQ_API_KEY
 */

const CONFLUENCE_PAGE_ID = process.env.CONFLUENCE_PAGE_ID;
const CONFLUENCE_BASE_URL = (process.env.CONFLUENCE_BASE_URL || '').replace(/\/$/, '');
const CONFLUENCE_EMAIL = process.env.CONFLUENCE_EMAIL;
const CONFLUENCE_API_TOKEN = process.env.CONFLUENCE_API_TOKEN;
const GITHUB_TOKEN = process.env.GITHUB_TOKEN;
const PR_NUMBER = process.env.PR_NUMBER;
const REPO_OWNER = process.env.REPO_OWNER;
const REPO_NAME = process.env.REPO_NAME;
const GROQ_API_KEY = process.env.GROQ_API_KEY;

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
requireEnv('GROQ_API_KEY');

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

// ── Groq AI ──

const MAX_DIFF_CHARS = 30000;
const MAX_SUMMARY_CHARS = 8000;

function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

async function regenerateSummary(existingSummary, diff, pr, files) {
  const truncatedDiff =
    diff.length > MAX_DIFF_CHARS
      ? diff.slice(0, MAX_DIFF_CHARS) + '\n\n... [diff truncated] ...'
      : diff;

  const truncatedSummary =
    existingSummary.length > MAX_SUMMARY_CHARS
      ? existingSummary.slice(0, MAX_SUMMARY_CHARS) + '\n... [summary truncated] ...'
      : existingSummary;

  const fileList = files
    .map((f) => `${f.status} ${f.filename} (+${f.additions} -${f.deletions})`)
    .join('\n');

  const prompt = `You are updating a project summary document on Confluence. You have two inputs:

1. THE EXISTING PROJECT SUMMARY (current content of the Confluence page):
---
${truncatedSummary}
---

2. A NEW PULL REQUEST THAT WAS JUST MERGED:
- PR Title: ${pr.title}
- PR Description: ${pr.body || '(none)'}
- Merged on: ${pr.mergedAt}
- PR Link: ${pr.htmlUrl}
- Files changed:
${fileList}

- Code diff:
\`\`\`
${truncatedDiff}
\`\`\`

YOUR TASK:
Rewrite the ENTIRE project summary, incorporating the changes from this PR. The updated summary should:

1. Keep all existing information that is still accurate.
2. UPDATE any sections that are affected by the PR changes (e.g., if the PR changes how a feature works, update that feature's description).
3. ADD new information about what this PR introduced or changed.
4. Include a "Recent Changes" section at the bottom with a brief log entry for this PR (date, PR link, what changed).
5. Be written in clear, non-technical language where possible — this is a project summary for the team, not a code review.
6. Keep it concise but comprehensive.

OUTPUT FORMAT:
- Write the output as Confluence storage format HTML.
- Use these tags: <h1>, <h2>, <h3>, <p>, <ul>, <li>, <strong>, <em>, <a>, <code>, <table>, <tr>, <th>, <td>.
- Do NOT wrap the output in \`\`\` code blocks.
- Do NOT include any preamble like "Here is the updated summary". Just output the HTML directly.
- The output replaces the ENTIRE page body, so include everything.`;

  const models = ['llama-3.3-70b-versatile', 'llama-3.1-8b-instant'];

  for (const model of models) {
    for (let attempt = 1; attempt <= 3; attempt++) {
      console.log(`Calling Groq (${model}, attempt ${attempt})...`);

      const res = await fetch('https://api.groq.com/openai/v1/chat/completions', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${GROQ_API_KEY}`,
        },
        body: JSON.stringify({
          model,
          messages: [{ role: 'user', content: prompt }],
          max_tokens: 4000,
          temperature: 0.3,
        }),
      });

      if (res.ok) {
        const data = await res.json();
        let content = data.choices?.[0]?.message?.content || '';
        content = content.replace(/```html\s*/gi, '').replace(/```\s*/g, '').trim();
        if (content.length > 50) return content;
        console.log('Response too short, retrying...');
      } else {
        const errText = await res.text();
        console.error(`Groq API error (${model}, attempt ${attempt}): ${res.status} ${errText}`);
        if (res.status === 429) {
          const waitSec = 10 * attempt;
          console.log(`Rate limited. Waiting ${waitSec}s...`);
          await sleep(waitSec * 1000);
          continue;
        }
      }
      break;
    }
    console.log(`Model ${model} failed, trying next...`);
  }

  return null;
}

// ── Main ──

async function main() {
  console.log('Step 1: Fetching PR details, diff, and files...');
  const [pr, diff, files] = await Promise.all([
    fetchPrDetails(),
    fetchPrDiff(),
    fetchPrFiles(),
  ]);
  console.log(`PR: "${pr.title}" | ${files.length} files | diff: ${diff.length} chars`);

  console.log('Step 2: Fetching current Confluence page...');
  const { currentStorage, version, title, status } = await getConfluencePage();
  console.log(`Current page: "${title}" | version: ${version} | content: ${currentStorage.length} chars`);

  console.log('Step 3: Sending to AI to regenerate project summary...');
  const newSummary = await regenerateSummary(currentStorage, diff, pr, files);

  if (!newSummary) {
    console.error('AI failed to generate summary. Confluence page not updated.');
    process.exit(1);
  }

  console.log(`AI generated new summary: ${newSummary.length} chars`);

  console.log('Step 4: Updating Confluence page with new summary...');
  await updateConfluencePage(newSummary, version, title, status, `Updated via PR #${PR_NUMBER}: ${pr.title}`);
  console.log('Done! Confluence project summary regenerated with PR changes.');
}

main().catch((err) => {
  console.error(err);
  process.exit(1);
});
