package com.ryancphil.pokedex.pokedex.presentation.detail

data class PokemonDetailState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val pokemonState: PokemonState = PokemonState()
)