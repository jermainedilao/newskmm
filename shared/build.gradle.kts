import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.5.20"
    id("com.android.library")
    id("com.codingfeline.buildkonfig")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }

    sourceSets {
        val ktorVersion = "1.6.2"

        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0-native-mt")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")

//                implementation("io.ktor:ktor-client-auth:$ktorVersion")
//                implementation("io.ktor:ktor-client-logging:$ktorVersion")
//                implementation("io.ktor:ktor-client-cio:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:1.2.5")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.ktor:ktor-client-cio-jvm:$ktorVersion")
                implementation("io.ktor:ktor-client-auth-jvm:$ktorVersion")
                implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization-jvm:$ktorVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
//                implementation("io.ktor:ktor-client-auth-ios:$ktorVersion")
//                implementation("io.ktor:ktor-client-logging-ios:$ktorVersion")
//                implementation("io.ktor:ktor-client-serialization-ios:$ktorVersion")
            }
        }
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(26)
        targetSdkVersion(31)
    }
}

// Reference: https://github.com/yshrsmz/BuildKonfig
buildkonfig {
    packageName = "com.jermaine.newskmm"
    defaultConfigs {
        buildConfigField(STRING, "news_api_key", getApiKey())
    }
}

// Reference: https://stackoverflow.com/a/59053039/5285687
fun getApiKey(): String {
    val items = HashMap<String, String>()
    val secret = rootProject.file("secret.properties")

    (secret.exists()).let {
        secret.forEachLine {
            val split = it.split("=")
            items[split[0]] = split[1]
        }
    }

    return items["news_api_key"]!!
}