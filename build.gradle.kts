buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Kotlin.gradlePlugin)
        classpath(Android.buildGradle)
        classpath(Android.buildKonfig)
    }
}

plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.1.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
