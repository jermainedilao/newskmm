pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "NewsKMM"
include(":androidApp")
include(":article-shared")
include(":article:article_list")
include(":android-core")
include(":article:common-ui")
include(":article:article_details")
