enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") // Syntactical nice-to-have for gradle dependency management
pluginManagement {
    includeBuild("build-logic") // multi-module gradle configuration
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
rootProject.name = "Pokedex"
include(":app")
