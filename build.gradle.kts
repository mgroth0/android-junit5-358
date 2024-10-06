@file:Suppress("UnstableApiUsage")

import org.gradle.api.JavaVersion.VERSION_21

plugins {
    kotlin("multiplatform") version("2.0.20")
    id("com.android.library") version "8.5.2"
    id("de.mannodermaus.android-junit5") version "1.11.2.0"
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Wrapper> {
    gradleVersion = "8.10.2"
}

repositories {
    mavenCentral()
    google()
}


kotlin {
    androidTarget()
}


android {
    namespace = "bug"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = VERSION_21
        targetCompatibility = VERSION_21
    }
    defaultConfig {
        minSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["runnerBuilder"] ="de.mannodermaus.junit5.AndroidJUnit5Builder"
    }
    testOptions {
        managedDevices {
            localDevices.apply {
                register("myAndroidEmulator") {
                    device = "Nexus One"
                    apiLevel = 33
                    systemImageSource ="aosp-atd"
                }
            }
        }
    }
}

dependencies {
    val jupiter = "5.11.2"
    val mannodermaus = "1.6.0"
    val cfg = "androidInstrumentedTestImplementation"
    add(cfg,"org.junit.jupiter:junit-jupiter-api:$jupiter")
    add(cfg,"org.junit.jupiter:junit-jupiter-params:$jupiter")
    add(cfg,"de.mannodermaus.junit5:android-test-core:$mannodermaus")
    add(cfg,"de.mannodermaus.junit5:android-test-runner:$mannodermaus")
}