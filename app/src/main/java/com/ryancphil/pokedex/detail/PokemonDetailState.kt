package com.ryancphil.pokedex.detail

data class PokemonDetailState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val name: String = "",
    val types: String = "",
    val sprite: String = "",
    val height: String = "",
    val weight: String = "",
    val statsTitle: String = "",
    val stats: List<String> = emptyList(),
    val abilitiesTitle: String = "",
    val abilities: List<String> = emptyList(),
    val movesTitle: String = "",
    val moves: List<String> = emptyList()
)
