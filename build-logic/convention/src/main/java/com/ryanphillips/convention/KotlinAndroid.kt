package com.ryanphillips.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/**
 * Configure base Kotlin with Android options.
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*,*,*,*,*,*>
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("projectCompileSdk").get().toString().toInt()
        defaultConfig{
            minSdk = libs.findVersion("projectMinSdk").get().toString().toInt()
        }
    }

    configureKotlinAndroid()
}

/*
 * Configure Kotlin Android options.
 */
internal fun Project.configureKotlinAndroid() {
    // DSL doesn’t register accessor for Android variant, so we pass the java class literal.
    extensions.configure(KotlinAndroidProjectExtension::class.java) {
        jvmToolchain(11)
        compilerOptions {
            freeCompilerArgs.set(
                listOf("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
            )
        }
    }
}

internal fun Project.configureKotlinJvm() {
    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(11)
    }
}