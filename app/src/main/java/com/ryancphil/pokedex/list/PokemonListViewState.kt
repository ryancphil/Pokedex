package com.ryancphil.pokedex.list

data class PokemonListViewState(
    val isLoading: Boolean = false,
    val names: List<String> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)