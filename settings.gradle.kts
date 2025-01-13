include(
    ":app",

    ":common-base",
    ":common-ui",
    ":common-test",
    ":common-storage",

    ":feature-photo-day",
    ":feature-profile",
    ":feature-planets",
    ":feature-asteroids"
)

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Cosmos"
include()
