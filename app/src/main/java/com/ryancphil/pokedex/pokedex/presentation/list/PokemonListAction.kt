package com.ryancphil.pokedex.pokedex.presentation.list

interface PokemonListAction {
    data class OnPokemonClick(val id: Int): PokemonListAction
    data object OnLoadMore: PokemonListAction
}