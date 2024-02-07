@file:OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)

plugins {
  id("com.android.application")
  kotlin("android")
  id("org.jetbrains.compose")
}

android {
  namespace = "dev.reprator.khatabook.android"
  compileSdk = 34

  defaultConfig {
    applicationId = "dev.reprator.khatabook.android"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.9"
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
}

dependencies {
  implementation(project(":shared"))

  implementation(compose.runtime)
  implementation(compose.foundation)
  implementation(compose.material3)
  implementation(compose.preview)
  implementation(compose.uiTooling)
  implementation(libs.decompose)
  implementation(libs.decompose.router)
  implementation(libs.androidx.activity.compose)

  implementation(libs.koin.android)
}
