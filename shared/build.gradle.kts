import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

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
                implementation(Kotlin.coroutinesCoreNative)
                implementation(Ktor.clientCore)
                implementation(Ktor.clientSerialization)
                implementation(Ktor.logbackClassic)
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
                implementation(Ktor.clientAndroid)
                implementation(Ktor.clientCioJvm)
                implementation(Ktor.clientAuthJvm)
                implementation(Ktor.clientLoggingJvm)
                implementation(Ktor.clientSerializationJvm)
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
                implementation(Ktor.clientIos)
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
