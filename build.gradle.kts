plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    kotlin("android") version "1.9.10" apply false
    kotlin("multiplatform") version "1.9.10" apply false
    alias(libs.plugins.jetbrainsCompose) apply false
}
