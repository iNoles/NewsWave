import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinPluginSerialization)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.compose.compiler)
}

kotlin {
    targets.all {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    freeCompilerArgs = listOf("-Xexpect-actual-classes")
                }
            }
        }
    }

    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_11)
                }
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            binaryOption("bundleId", "com.jonathansteele.news")
        }
    }

    sourceSets {
        val desktopMain by getting
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation("org.jetbrains.compose.material3:material3-window-size-class:1.9.0-alpha03")
            implementation("org.jetbrains.compose.material:material-icons-core:1.7.3")
            implementation(compose.components.resources)
            implementation(libs.kotlinx.datetime)
            implementation(libs.fuel.kotlinx.serialization)
            implementation(libs.coil)
            implementation(libs.coil.network.core)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "com.jonathansteele.newswave"
    compileSdk = 36

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        applicationId = "com.jonathansteele.newswave"
        minSdk = 26
        targetSdk = 36
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
    /*dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }*/
}

compose.desktop {
    application {
        mainClass = "com.jonathansteele.news.MainKt"

        buildTypes.release.proguard {
            configurationFiles.from("proguard-rules.pro")
        }

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.jonathansteele.news"
            packageVersion = "1.0.0"
        }
    }
}

buildkonfig {
    packageName = "com.jonathansteele.newswave"

    val localPropsFile = rootProject.file("local.properties")
    val localProperties = Properties()
    if (localPropsFile.exists()) {
        runCatching {
            localProperties.load(localPropsFile.inputStream())
        }.getOrElse {
            it.printStackTrace()
        }
    }
    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING,
            "NEWS_API_KEY",
            localProperties["news_api_key"]?.toString() ?: "",
        )
    }
}
