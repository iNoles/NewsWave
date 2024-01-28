plugins {
    // trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication) apply false
    kotlin("android") version "1.9.22" apply false
    kotlin("multiplatform") version "1.9.22" apply false
    alias(libs.plugins.jetbrainsCompose) apply false
}
