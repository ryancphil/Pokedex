package com.ryanphillips.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension,
    extensionType: ExtensionType
) {
    commonExtension.buildFeatures.buildConfig = true

    when (extensionType) {
        ExtensionType.APPLICATION -> extensions.configure<ApplicationExtension> {
            buildTypes {
                getByName("debug") {
                    configureDebug()
                }
                getByName("release") {
                    configureRelease(commonExtension)
                }
            }
        }
        ExtensionType.LIBRARY -> extensions.configure<LibraryExtension> {
            buildTypes {
                getByName("debug") {
                    configureDebug()
                }
                getByName("release") {
                    configureRelease(commonExtension)
                }
            }
        }
    }
}

private fun BuildType.configureDebug() {
    // buildConfigField(...) could be initialized here if needed.
}

private fun BuildType.configureRelease(
    commonExtension: CommonExtension
) {
    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}