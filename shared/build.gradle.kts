plugins {
    id(libs.plugins.kotlin.multiplatform.get().pluginId)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.21"
    id(libs.plugins.kotlin.native.cocoapods.get().pluginId)
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jb.compose.get().pluginId)
}

kotlin {
    android()

    jvm("desktop")

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.json)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.serialization.json)

                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization.core)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines.test)
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        val androidMain by getting {
            dependencies {
                api(libs.androidx.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.androidx.core)

                implementation(libs.ktor.client.android)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies{
                implementation(libs.ktor.client.darwin)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.ktor.client.java)
            }
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "dev.reprator.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
