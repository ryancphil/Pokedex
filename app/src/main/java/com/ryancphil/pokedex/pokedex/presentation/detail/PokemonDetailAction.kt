package com.ryancphil.pokedex.pokedex.presentation.detail

sealed interface PokemonDetailAction {
    data object OnBackClick: PokemonDetailAction
}
