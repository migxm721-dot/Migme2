# Build Instructions

Detailed guide for building the migme Android APK.

---

## Local build

### Debug

```bash
./gradlew assembleDebug
```

Output: `build/outputs/apk/<flavor>/debug/migmeDroid-<flavor>-debug-<version>.apk`

### Release

```bash
export RELEASE_KEYSTORE_PATH=/path/to/release.jks
export KEYSTORE_PASSWORD=<keystore-password>
export KEY_ALIAS=<key-alias>
export KEY_PASSWORD=<key-password>
./gradlew assembleRelease
```

Output: `build/outputs/apk/<flavor>/release/migmeDroid-<flavor>-release-<version>.apk`

### All variants

```bash
./gradlew assemble
```

---

## CI/CD build (GitHub Actions)

Builds are triggered automatically on push to `main`, `master`, or `develop`.

Artifacts are available in the **Actions → run → Artifacts** section:

| Artifact | Retention |
|----------|-----------|
| `migme-debug-<run>` | 30 days |
| `migme-release-<run>` | 90 days |

---

## ProGuard / R8

Release builds have minification and resource shrinking enabled. ProGuard rules are in
`proguard-rules.pro`. If you add new libraries, add corresponding keep rules there.

---

## Flavors

| Flavor | Description |
|--------|-------------|
| `full` | Standard release |
| `special` | Special purpose build (e.g. disabled region checks) |
