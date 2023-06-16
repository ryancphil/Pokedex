package com.ryancphil.pokedex.list

data class PokemonListState(
    val isLoading: Boolean = false,
    val names: List<String> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)