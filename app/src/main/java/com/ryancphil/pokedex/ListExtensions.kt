package com.ryancphil.pokedex

fun List<String>.capitalizeAll(): List<String> {
    return map { string ->
        string.replaceFirstChar {
            it.uppercase()
        }
    }
}