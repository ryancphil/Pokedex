package com.ryanphillips.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/*
 * Extend gradle's Project object with a "libs" variable that points to our Version Catalog
 * for easy access to dependencies in our Convention Plugins.
 */
val Project.libs
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

