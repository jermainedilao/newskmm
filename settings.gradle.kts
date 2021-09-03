pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "NewsKMM"
include(":androidApp")
include(":shared")
include(":article:article_list")
include(":components")
include(":article:common-ui")
include(":article:article_details")
