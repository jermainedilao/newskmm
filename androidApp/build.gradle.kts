plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
}

val composeVersion = "1.1.0-alpha02"

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.20-RC")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    // Compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.activity:activity-compose:1.3.0-beta02")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha05")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.17.0")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$composeVersion")
    implementation("io.coil-kt:coil-compose:1.3.2")

    implementation("com.google.accompanist:accompanist-swiperefresh:0.17.0")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.jermaine.newskmm.android"
        minSdkVersion(26)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
        kotlinCompilerVersion = "1.5.10"
    }
}