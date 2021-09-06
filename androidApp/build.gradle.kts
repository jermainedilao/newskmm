plugins {
    id("com.android.application")
    id("kotlin-parcelize")
    kotlin("android")
}

dependencies {
    implementation(project(":article-shared"))
    implementation(project(":article:article_list"))
    implementation(project(":article:article_details"))
    implementation(project(":components"))

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
        kotlinCompilerExtensionVersion = Compose.version
        kotlinCompilerVersion = Kotlin.version
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.name
    }
    tasks {
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions { jvmTarget = "11" }
        }
    }
}
