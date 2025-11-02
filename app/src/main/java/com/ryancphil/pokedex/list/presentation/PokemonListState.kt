package com.ryancphil.pokedex.list.presentation

data class PokemonListState(
    val isLoading: Boolean = false,
    val names: List<String> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val currentPage: Int = 0
)