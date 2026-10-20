# AGENTS.md

Welcome to **Spaces** (`raquezha/spaces`), an open-source developer-centric productivity shell built with Kotlin Multiplatform (KMP).

---

## 🚨 CRITICAL OPEN-SOURCE & ZERO-PII RULES

This repository is public and open-source. All agents executing tasks in this codebase must strictly adhere to the following safety rules:

1. **ZERO COMPANY PII OR PRIVATE DOMAINS:**
   - NEVER commit company names, intranet domains, private GitLab hosts, internal wiki/Outline URLs, or employee names into code, comments, sample data, or fixtures.
   - All sample and mock data MUST use fictional neutral placeholders (e.g., `Dev Jane`, `sample-repo`, `feature/awesome-tool`).
2. **HOST-AGNOSTIC ABSTRACTIONS:**
   - NEVER hardcode private host URLs in connectors.
   - All connectors must accept runtime `hostUrl` parameters with public defaults (`https://gitlab.com`, `https://api.github.com`).
   - Self-hosted instances are configured dynamically by the user at runtime in Settings.
3. **SECRET ISOLATION:**
   - NEVER commit API tokens, OAuth client IDs, client secrets, or Apple Developer signing materials (`*.p12`, `*.p8`, `*.cer`, `*.mobileprovision`, `Secrets.xcconfig`).
   - Credentials must be persisted on-device only via native platform vaults (macOS/iOS Keychain and Android EncryptedSharedPreferences).

---

## ARCHITECTURAL PRINCIPLES

1. **Pure Client-Side Architecture (No Custom Backend):**
   - Spaces does NOT have a custom backend API server or cloud database.
   - The mobile client queries existing third-party REST APIs (GitLab, GitHub, Outline) directly via Ktor.
2. **Native Idiomatic UIs:**
   - **Android:** Jetpack Compose (`androidApp`).
   - **iOS:** Native SwiftUI (`iosApp`) consuming the shared KMP framework.
   - Do not use Compose Multiplatform for iOS.
3. **Preview-First Development:**
   - All UI components must support instant previewing via `@Preview` (Compose) and `#Preview` (SwiftUI) using mock data (`MockActivityConnector`) without requiring network connectivity.
4. **Hero Feature Focus (V1):**
   - The primary feature is the **Weekly Developer Report Engine**:
     - Connects to GitLab (OAuth 2.0 PKCE / PAT) and GitHub (PAT).
     - Pulls authenticated activity for the date window (last 7 days).
     - Normalizes items into `DevActivity`.
     - Categorizes activity into **Major** (merged features), **Minor** (chores, bumps), and **Other** (reviews).
     - Captures **Blockers** and **Personal Notes**.
     - Provides a 1-tap **Copy as Markdown** button formatted for team meetings.

---

## CORE CONTRACTS (`shared/commonMain`)

- `ActivityConnector`: Host-agnostic interface for activity sources.
- `DevActivity`: Normalized cross-platform activity item.
- `WeeklyReport`: Aggregate report model with markdown serialization.
- `DestinationPublisher`: Pluggable output target (e.g. `ClipboardPublisher`, `OutlinePublisher`).

---

## PROJECT TRACKING

Tasks are tracked on the GitHub Project board:
- **Board:** [Spaces V1](https://github.com/users/raquezha/projects/4)
- **Issues:** #1 to #9 in `raquezha/spaces`
