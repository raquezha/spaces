# AGENTS.md

Welcome to **Spaces** (`raquezha/spaces`).

> **"A clean mobile app that looks at your week's work and writes your report for you."**

Inside the app, you have your spaces: a workspace for team repos, a devspace for personal hacks and open-source, or whatever context you work in.

---

## 🚨 OPEN-SOURCE & ZERO-PII RULES

This is a public open-source repository.

1. **NO COMPANY PII OR PRIVATE DOMAINS:**
   - NEVER commit company names, intranet URLs, private domains, or employee names into code, comments, sample data, or fixtures.
   - All preview mocks and unit test fixtures MUST use fictional names (e.g. `Dev Jane`, `sample-project`).
2. **HOST-AGNOSTIC:**
   - Connectors take a configurable `hostUrl` (defaulting to public `gitlab.com` or `api.github.com`).
   - Self-hosted instances are configured by the user at runtime in Settings.
3. **ZERO LEAKS:**
   - Never commit tokens, client secrets, or Apple signing certificates (`*.mobileprovision`, `*.p12`, `Secrets.xcconfig`).
   - Credentials stay on-device in native Keychain (iOS) and EncryptedSharedPreferences (Android).

---

## THE CORE STACK

- **Shared Core (KMP):** Pure Kotlin in `shared/commonMain` (Ktor, Coroutines, DateTime, serialization). Zero custom backend servers.
- **Android UI:** Jetpack Compose with Material 3.
- **iOS UI:** Native SwiftUI consuming the shared KMP framework.
- **Preview-First:** Every screen must render instantly in `@Preview` and `#Preview` using mock data.

---

## WHAT SPACES DOES (V1)

1. Connects to your code host (GitLab / GitHub).
2. Grabs your last 7 days of merged PRs/MRs, commits, and reviews.
3. Groups them into **Major**, **Minor**, and **Other**.
4. Gives you quick text fields for **Blockers** and **Notes**.
5. 1-tap **"Copy as Markdown"** so your Thursday morning report is finished in 30 seconds.
