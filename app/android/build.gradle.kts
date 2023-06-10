@file:OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
  id("com.android.application")
  kotlin("android")
  id("org.jetbrains.compose")
}

android {
  namespace = "dev.reprator.khatabook.android"
  compileSdk = 33

  defaultConfig {
    applicationId = "dev.reprator.khatabook.android"
    minSdk = 24
    targetSdk = 33
    versionCode = 1
    versionName = "1.0"
  }

  buildFeatures {
    compose = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
}

// TODO: Remove once a compiler with support for >1.8.21 available
compose {
  kotlinCompilerPlugin.set(dependencies.compiler.forKotlin("1.8.20"))
  kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.8.21")
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
}
