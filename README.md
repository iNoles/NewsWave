# News App 🗞
[![GitHub license](https://img.shields.io/github/license/iNoles/News)](https://github.com/iNoles/News/blob/main/LICENSE)

News App is a simple news app 🗞️ that uses [NewsAPI](https://newsapi.org/) to fetch top news headlines from the API. The main aim of this app is to be a leading example of how to build a Kotlin Multiplatform project targeting Android, iOS, and Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

## Supported Platforms
- Android
- iOS
- Desktop

## Stack
- 🍎 Shared UI - [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform)
- 🌎 Network - [Fuel](https://github.com/kittinunf/fuel)
- 🛢 Image Loading - [Coil](https://github.com/coil-kt/coil)

<img alt="NewsApp Main Page" height="450px" src="https://raw.githubusercontent.com/iNoles/News/main/screenshots/mac-desktop.png" />
