# Migme Android Client

Android client re-engineered to support fragments.

## Build Instructions

### Prerequisites

- JDK 17
- Android SDK with API 35
- Gradle 8.2

### Repository Setup

Clone the main repository and all library dependencies into sibling directories:

```bash
git clone https://github.com/migxm721-dot/Migme2.git
git clone https://github.com/migxm721-dot/mig33JavaClientFramework.git
git clone https://github.com/migxm721-dot/mig33FusionClientService.git
git clone https://github.com/migxm721-dot/client-miniblog.git
git clone https://github.com/migxm721-dot/mig33ClientNetworkService.git
git clone https://github.com/migxm721-dot/common-lib.git
git clone https://github.com/migxm721-dot/localization-lib.git
```

All repositories must be cloned into the **same parent directory** so that relative paths (`../libname`) resolve correctly.

### Building Locally

```bash
cd Migme2

# Build Debug APK
./gradlew assembleFullDebug

# Build Release APK (requires signing credentials)
export ANDROID_KEYSTORE_PASSWORD=<your_keystore_password>
./gradlew assembleFullRelease

# Run unit tests
./gradlew testFullDebugUnitTest
```

Output APKs are placed in `build/outputs/apk/`.

### CI/CD (GitHub Actions)

The workflow at `.github/workflows/android-build.yml` automatically:

- Checks out all library repositories
- Sets up JDK 17 and Android SDK
- Caches Gradle dependencies
- Builds the **Debug APK** on every push/PR
- Builds the **Release APK** on pushes to `main`/`master` only
- Uploads APKs as downloadable artifacts (Debug: 14 days, Release: 30 days)
- Runs unit tests and generates a build summary

#### Required GitHub Secrets

| Secret | Description |
|--------|-------------|
| `REPO_ACCESS_TOKEN` | Personal access token with `repo` scope to checkout private library repositories |
| `ANDROID_KEYSTORE_PASSWORD` | Password for the release keystore (`appsigning/keystore_2010_android.jks`) |

### Signing Configuration

- **Debug** signing uses `appsigning/debug/debug.keystore` (included in repo)
- **Release** signing uses `appsigning/keystore_2010_android.jks` with `ANDROID_KEYSTORE_PASSWORD` environment variable

### Key Dependencies

| Dependency | Version | Notes |
|------------|---------|-------|
| Android Gradle Plugin | 8.2.0 | |
| Gradle | 8.2 | |
| Glide | 4.16.0 | Image loading (replaces UniversalImageLoader) |
| Firebase Analytics | 21.5.0 | Analytics (replaces Google Play Services Analytics) |
| LeakCanary | 1.3.1 | Memory leak detection (debug only) |

### Target Configuration

- `compileSdk`: 35
- `targetSdk`: 35
- `minSdk`: 24
- Java: 17

