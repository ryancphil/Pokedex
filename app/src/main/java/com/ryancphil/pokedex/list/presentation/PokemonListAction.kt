package com.ryancphil.pokedex.list.presentation

interface PokemonListAction {
    data object OnPokemonClick: PokemonListAction
    data object OnLoadMore: PokemonListAction
}