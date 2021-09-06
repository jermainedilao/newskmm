plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
}

dependencies {
    implementation(project(":article-shared"))
    implementation(project(":components"))
    implementation(project(":android-core"))
    api(project(":article:common-ui"))

    implementation(Google.material)
    implementation(Kotlin.stdlib)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.annotation)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.liveDataKtx)
    implementation(AndroidX.viewModelKtx)

    // Compose
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.uiTooling)
    implementation(Compose.activity)
    implementation(Compose.navigation)
    implementation(Compose.navigationAnimation)
    implementation(Compose.runtime)
    implementation(Compose.runtimeLiveData)
    implementation(Compose.coil)
    implementation(Compose.swipeRefresh)
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
