package com.ryancphil.pokedex.pokedex.domain.model

enum class Type {
    FIGHTING,
    PSYCHIC,
    POISON,
    DRAGON,
    GHOST,
    DARK,
    GROUND,
    FIRE,
    FAIRY,
    WATER,
    FLYING,
    NORMAL,
    ROCK,
    ELECTRIC,
    BUG,
    GRASS,
    ICE,
    STEEL;

    companion object {
        fun from(apiKey: String): Type? {
            return entries.firstOrNull { it.name.lowercase() == apiKey }
        }
    }
}