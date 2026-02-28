# migme Android Client

[![Android CI/CD](https://github.com/migxm721-dot/Migme2/actions/workflows/android-build.yml/badge.svg)](https://github.com/migxm721-dot/Migme2/actions/workflows/android-build.yml)

Android client re-engineered to support fragments.

---

## 📥 Download Latest APK

1. Go to the [Actions](https://github.com/migxm721-dot/Migme2/actions) tab.
2. Click the latest successful workflow run.
3. Scroll to the **Artifacts** section and download the APK.

---

## 🛠️ Tech Stack

| Component | Technology |
|-----------|-----------|
| Language | Java |
| Build system | Gradle 8.2 / AGP 8.2.0 |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 35 (Android 15) |
| Image loading | Glide 4.16.0 |
| Analytics | Firebase Analytics |
| Dependency injection | — |
| Networking | Internal framework |

---

## 🏗️ Build Instructions

### Prerequisites

- JDK 17
- Android SDK with API 35 (install via Android Studio or `sdkmanager`)
- All internal library repositories cloned as siblings of this repo (see below)

### Repository layout

```
workspace/
├── Migme2/                    ← this repo
├── mig33JavaClientFramework/
├── mig33FusionClientService/
├── client-miniblog/
├── mig33ClientNetworkService/
├── common-lib/
└── localization-lib/
```

Clone the companion repos:

```bash
git clone https://github.com/migxm721-dot/mig33JavaClientFramework.git
git clone https://github.com/migxm721-dot/mig33FusionClientService.git
git clone https://github.com/migxm721-dot/client-miniblog.git
git clone https://github.com/migxm721-dot/mig33ClientNetworkService.git
git clone https://github.com/migxm721-dot/common-lib.git
git clone https://github.com/migxm721-dot/localization-lib.git
```

### Build locally

```bash
# Debug APK
./gradlew assembleDebug

# Release APK (requires keystore env vars – see Signing below)
./gradlew assembleRelease
```

### CI/CD (GitHub Actions)

Pushing to `main`, `master`, or `develop` automatically triggers a build.
Pull requests targeting `main` or `master` also trigger a build.

You can also trigger a manual build via **Actions → Android CI/CD → Run workflow**.

---

## ✍️ Signing

### Debug builds

The debug keystore at `appsigning/debug/debug.keystore` is used automatically.

### Release builds

Release signing uses environment variables. Set the following **GitHub Secrets**:

| Secret | Description |
|--------|-------------|
| `KEYSTORE_BASE64` | Base64-encoded release keystore (`.jks`) |
| `KEYSTORE_PASSWORD` | Keystore password |
| `KEY_ALIAS` | Key alias inside the keystore |
| `KEY_PASSWORD` | Key password |

Encode your keystore:

```bash
# Linux
base64 your-release.jks > keystore_base64.txt
# macOS
base64 -i your-release.jks > keystore_base64.txt
# Paste the contents of keystore_base64.txt into the KEYSTORE_BASE64 secret
```

For local release builds, set environment variables directly:

```bash
export RELEASE_KEYSTORE_PATH=/path/to/release.jks
export KEYSTORE_PASSWORD=<password>
export KEY_ALIAS=<alias>
export KEY_PASSWORD=<key-password>
./gradlew assembleRelease
```

---

## 🔥 Firebase / Google Services

This project uses Firebase Analytics. You must provide a `google-services.json` file
(excluded from version control) in the project root for Firebase to work.

- For local development: download from the [Firebase Console](https://console.firebase.google.com/) and place at the root of this repo.
- For CI/CD: add the file contents as a `GOOGLE_SERVICES_JSON` secret and write it to disk before building.

---

## 📦 Installation

1. Build or download the APK.
2. Enable **Install from unknown sources** on the target device.
3. Transfer the APK and tap to install.

---

## 📄 License

Proprietary – all rights reserved.

