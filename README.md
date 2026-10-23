# Spaces 🐾

> **A clean mobile app that looks at your week's work and writes your report for you.**

Spaces is an open-source **Kotlin Multiplatform (KMP)** application for Android and iOS. It connects to your developer workspaces (GitLab, GitHub), gathers your merged pull requests, commits, and code reviews from the past 7 days, and synthesizes a clean, standup-ready report with one tap.

---

## ✨ Features

- **Personal Report Synthesis:** Automatically categorizes your engineering week into **Major** (shipped features), **Minor** (chores, maintenance), and **Other** (reviews, discussions).
- **1-Tap Markdown Export:** Copies formatted markdown directly to your clipboard, ready for team meetings, Slack, Telegram, or internal wikis.
- **Pure Client-Side & Private:** **Zero middleman servers.** All API queries to code hosts run directly from your device using Ktor HTTPS calls. Credentials stay encrypted in your local OS vault.
- **Native Platform Experience:**
  - **Android:** 100% Jetpack Compose with Material 3.
  - **iOS:** Native SwiftUI consuming the shared KMP framework.
- **Preview-First Development:** UI screens render instantly in Android Studio (`@Preview`) and Xcode (`#Preview`) using built-in mock fixtures without requiring network access.

---

## 🛠 Tech Stack

| Layer | Technology |
|---|---|
| **Core Shared Framework** | Kotlin Multiplatform 2.x, Coroutines, Flow |
| **Networking** | Ktor 3.x with platform engines (`OkHttp` on Android, `Darwin` on iOS) |
| **Date & Time** | `kotlinx-datetime` |
| **Serialization** | `kotlinx-serialization-json` |
| **Android UI** | Jetpack Compose + Material 3 |
| **iOS UI** | Native SwiftUI (iOS 17+) |
| **Security** | Apple Keychain (`iosMain`) & EncryptedSharedPreferences (`androidMain`) |

---

## 🚀 Getting Started

### Prerequisites
- JDK 17 or higher
- Android Studio Ladybug (or newer) with Android SDK 35
- Xcode 16+ (for iOS build)

### Running Android
```bash
./gradlew :androidApp:assembleDebug
```
Or open the root directory in Android Studio and run the `androidApp` configuration.

### Building iOS Shared Framework
```bash
./gradlew :shared:linkDebugFrameworkIosSimulatorArm64
```
Open `iosApp/` in Xcode to preview SwiftUI views or run in the iOS Simulator.

---

## 🗺 Roadmap

Work is tracked on the **[Spaces V1 Project Board](https://github.com/users/raquezha/projects/4)**:
- [x] **#1 Scaffold KMP Project Structure** (`shared`, `androidApp`, `iosApp`)
- [ ] **#2 Core ActivityConnector Abstractions & DevActivity Models**
- [ ] **#3 On-Device Token Storage** (Keychain & EncryptedSharedPreferences)
- [ ] **#4 GitLab Activity Connector** (OAuth 2.0 PKCE & Custom Host support)
- [ ] **#5 GitHub Activity Connector** (Personal Access Token)
- [ ] **#6 Activity Aggregator & Work Classifier** (Major / Minor / Other heuristics)
- [ ] **#7 Android Compose UI**
- [ ] **#8 iOS SwiftUI UI**
- [ ] **#9 TestFlight Distribution Strategy & Secret Isolation**

---

## 📖 Companion Notes & Articles

- **Blog Article:** *"I Asked My AI Bot Team to Design My App. Figma Was the Hard Part."*
- **Design Tokens:** Built with the W3C Design Tokens Community Group (DTCG) specification.

---

## 📄 License

MIT License. See [LICENSE](LICENSE) for details.
