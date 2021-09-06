plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {
    implementation(project(":shared-articles"))

    // Compose
    implementation(Compose.ui)
    implementation(Compose.uiTooling)
    implementation(Compose.material)
}

android {
    compileSdkVersion(Build.compileSdkVersion)
    defaultConfig {
        minSdkVersion(Build.minSdkVersion)
        targetSdkVersion(Build.targetSdkVersion)
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
        kotlinCompilerExtensionVersion = Compose.version
        kotlinCompilerVersion = Kotlin.version
    }
}
