package com.ryancphil.pokedex.pokedex.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val heightInMeters: Double,
    val weightInKilograms: Double,
    val sprite: String,
    val types: List<Type>,
    val baseStats: List<Stat>
)