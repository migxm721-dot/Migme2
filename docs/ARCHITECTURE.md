# Architecture Overview

High-level description of the migme Android client architecture.

---

## Module structure

```
workspace/
├── Migme2/                       Android application module
│   ├── src/                      Java source code
│   ├── res/                      Android resources
│   ├── assets/                   Asset files (app.properties, etc.)
│   ├── libs/                     Native (JNI) libraries
│   ├── facebookSDK/              Facebook Android SDK (library module)
│   ├── appsigning/               Signing keystores (not committed for release)
│   ├── build.gradle              Application build script
│   └── settings.gradle           Multi-project settings
│
├── common-lib/                   Shared utility classes
├── localization-lib/             String localisation helpers
├── mig33JavaClientFramework/     Core Java client framework
├── mig33FusionClientService/     Fusion protocol client service
├── mig33ClientNetworkService/    Network communication service
└── client-miniblog/              Mini-blog feature module
```

---

## Key packages

| Package | Description |
|---------|-------------|
| `com.projectgoth` | Root application package |
| `com.projectgoth.common` | Shared configuration and constants |
| `com.projectgoth.util` | Utility classes (logging, etc.) |
| `com.projectgoth.notification` | Push notification handling |

---

## Build flavors

| Flavor | Use case |
|--------|----------|
| `full` | Standard public release |
| `special` | Custom builds (feature flags, region overrides) |

---

## Third-party libraries

| Library | Purpose |
|---------|---------|
| Glide 4.16 | Image loading and caching |
| Firebase Analytics | Usage analytics |
| AndroidX AppCompat | Backwards-compatible UI components |
| AndroidX MultiDex | DEX split for large apps |
| Facebook SDK | Social login / sharing |
| Gson | JSON serialisation |
| LeakCanary | Memory leak detection (debug only) |
