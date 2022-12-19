pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Wangku"
include(
    ":app",
    ":data",
    ":domain"
)
include(":shared")
include(":shared:uikit")
include(":shared:network")
include(":core")
