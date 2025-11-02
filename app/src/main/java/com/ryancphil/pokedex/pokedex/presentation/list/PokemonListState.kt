package com.ryancphil.pokedex.pokedex.presentation.list

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemon: List<Pair<String, String>> = emptyList(), // name & sprite url
    val error: String? = null,
    val endReached: Boolean = false,
    val currentPage: Int = 0
)