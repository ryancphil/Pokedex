package com.ryancphil.pokedex.pokedex.presentation.list

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemon: List<PokemonListItemState> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val currentPage: Int = 0
)