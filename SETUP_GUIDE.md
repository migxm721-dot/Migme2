# Developer Setup Guide

Step-by-step instructions for setting up the migme Android project on a new machine.

---

## 1. Prerequisites

| Tool | Version | Install |
|------|---------|---------|
| JDK | 17 | [Adoptium Temurin](https://adoptium.net/) |
| Android Studio | Latest stable | [developer.android.com](https://developer.android.com/studio) |
| Git | Any recent | [git-scm.com](https://git-scm.com/) |

---

## 2. Clone all repositories

All internal libraries must live as **siblings** of this repository:

```bash
# Create a shared workspace directory
mkdir workspace && cd workspace

# Main application
git clone https://github.com/migxm721-dot/Migme2.git

# Internal libraries (must be siblings of Migme2/)
git clone https://github.com/migxm721-dot/mig33JavaClientFramework.git
git clone https://github.com/migxm721-dot/mig33FusionClientService.git
git clone https://github.com/migxm721-dot/client-miniblog.git
git clone https://github.com/migxm721-dot/mig33ClientNetworkService.git
git clone https://github.com/migxm721-dot/common-lib.git
git clone https://github.com/migxm721-dot/localization-lib.git
```

Expected directory layout:

```
workspace/
├── Migme2/
├── mig33JavaClientFramework/
├── mig33FusionClientService/
├── client-miniblog/
├── mig33ClientNetworkService/
├── common-lib/
└── localization-lib/
```

---

## 3. Android SDK setup

Install the required SDK components (using Android Studio SDK Manager or command line):

```bash
# Accept all licenses
yes | $ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager --licenses

# Install required components
$ANDROID_HOME/cmdline-tools/latest/bin/sdkmanager \
  "platforms;android-35" \
  "build-tools;35.0.0" \
  "platform-tools"
```

---

## 4. Firebase configuration

1. Go to the [Firebase Console](https://console.firebase.google.com/).
2. Select the migme project (or create one).
3. Download `google-services.json` for the Android app.
4. Place the file in the **root** of the `Migme2/` directory (next to `build.gradle`).

> ⚠️ Do **not** commit `google-services.json` to version control.

---

## 5. Build

```bash
cd Migme2

# Debug build
./gradlew assembleDebug

# Release build (requires signing config – see README.md)
./gradlew assembleRelease

# Run unit tests
./gradlew test
```

APK outputs are written to `build/outputs/apk/`.

---

## 6. Import into Android Studio

1. Open Android Studio.
2. Choose **Open** and select the `Migme2/` directory.
3. Allow the Gradle sync to complete.
4. Run on a device or emulator using the **Run** button.

---

## 7. Setting up release signing (CI/CD)

See [README.md – Signing](README.md#signing) for full instructions on:

- Encoding a keystore as base64
- Adding GitHub Secrets
- Triggering a signed release build via GitHub Actions

---

## 8. GitHub Secrets required for CI

| Secret | Description |
|--------|-------------|
| `KEYSTORE_BASE64` | Base64-encoded `.jks` keystore for release signing |
| `KEYSTORE_PASSWORD` | Keystore password |
| `KEY_ALIAS` | Key alias |
| `KEY_PASSWORD` | Key password |
| `GOOGLE_SERVICES_JSON` | Contents of `google-services.json` for Firebase (required for build) |
