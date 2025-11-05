package com.ryancphil.pokedex.pokedex.domain.model

data class Stat(
    val name: String,
    val value: Int
) {
    fun getAbbreviation(): String = when (name) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "SP-ATK"
        "special-defense" -> "SP-DEF"
        "speed" -> "SPD"
        else -> "?"
    }
}