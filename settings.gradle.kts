pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DIY Recipes"
include(":app")
include(":core")
include(":feature")
include(":core:common")
include(":core:designsystem")
include(":core:network")
include(":feature:meals")
include(":feature:cocktails")
include(":core:database")
