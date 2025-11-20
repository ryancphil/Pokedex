package com.ryancphil.pokedex.pokedex.domain.model

/**
 * Caching pokemon on the list call when I don't have
 * all of the info so default values for now.
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val sprite: String,
    val heightInMeters: Double = 0.0,
    val weightInKilograms: Double = 0.0,
    val types: List<Type> = emptyList(),
    val baseStats: List<Stat> = emptyList()
)