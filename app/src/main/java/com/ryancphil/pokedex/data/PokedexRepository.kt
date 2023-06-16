package com.ryancphil.pokedex.data

import com.ryancphil.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.data.model.PokemonResponse

interface PokedexRepository {

    suspend fun fetchPokemonList(offset: Int, limit: Int): PokemonListResponse?

    suspend fun fetchPokemonDetails(pokemonId: Int): PokemonResponse?
}