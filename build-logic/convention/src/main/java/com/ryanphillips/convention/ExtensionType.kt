package com.ryanphillips.convention

/*
 * Some Gradle config is shared between ApplicationExtension and LibraryExtension
 * types (eg. BuildTypes can be configured for both Application and Library extensions),
 * so this enum allows us to distinguish between the two.
 */
enum class ExtensionType {
    APPLICATION,
    LIBRARY
}