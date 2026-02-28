# Changelog

All notable changes to the migme Android client are documented here.

---

## [5.01.016] – Current

### Changed
- Build system migrated to Android Gradle Plugin 8.2.0 / Gradle 8.2
- Image loading library replaced: UniversalImageLoader → **Glide 4.16.0**
- Analytics replaced: Google Play Services Analytics → **Firebase Analytics**
- Deprecated `NineOldAndroids` library removed (native animations)
- Deprecated `fiksu` tracking SDK removed
- Samsung S Pen SDK removed (not available from public repositories)
- Deezer SDK removed (not available from public repositories)
- Internal library dependencies moved from Nexus to direct project references
- Added **GitHub Actions CI/CD** workflow for automated APK builds
- Updated `compileSdk` / `targetSdk` to **35**
- `minSdk` raised to **24** (Android 7.0)
- Enabled MultiDex
- Enabled ProGuard/R8 with resource shrinking for release builds
- Java source/target compatibility set to **17**
- Added Gradle parallel builds and caching

### Added
- `.github/workflows/android-build.yml` – automated CI/CD pipeline
- `SETUP_GUIDE.md` – developer setup instructions
- `docs/` – BUILD, CONTRIBUTING, CHANGELOG, ARCHITECTURE documentation
