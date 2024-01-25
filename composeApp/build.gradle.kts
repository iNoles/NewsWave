import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.9.21"
    id("com.android.application")
    id("org.jetbrains.compose")
    id("com.github.gmazzo.buildconfig") version "5.3.5"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    jvm("desktop")
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            implementation(libs.kotlinx.datetime)
            implementation(libs.fuel.kotlinx.serialization)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.ktx)
            implementation(libs.coil.compose)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.common)
            implementation(compose.desktop.currentOs)
        }
    }
}

buildConfig {
    val prop =
        Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "local.properties")))
        }
    packageName("com.jonathansteele.news")
    useKotlinOutput()
    buildConfigField("String", "NEWS_API_KEY", prop.getProperty("apikey"))
}

android {
    namespace = "com.jonathansteele.news.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.jonathansteele.news.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

compose.desktop {
    application {
        mainClass = "com.jonathansteele.news.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "News"
            packageVersion = "1.0.0"
            appResourcesRootDir.set(project.layout.projectDirectory.dir("resources"))
        }
    }
}
