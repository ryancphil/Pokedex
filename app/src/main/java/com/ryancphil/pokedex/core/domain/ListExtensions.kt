package com.ryancphil.pokedex.core.domain

fun List<String>.capitalizeAll(): List<String> {
    return map { string ->
        string.replaceFirstChar {
            it.uppercase()
        }
    }
}