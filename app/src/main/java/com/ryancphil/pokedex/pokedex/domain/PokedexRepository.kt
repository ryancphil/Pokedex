package com.ryancphil.pokedex.pokedex.domain

import com.ryancphil.pokedex.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.pokedex.data.model.PokemonResponse

interface PokedexRepository {

    // TODO: This repo abstraction lives in the domain and shouldn't depend on the raw Responses.
    // Create a domain DTO that the data layer can map to and that the presentation layer will be able to map to State.
    suspend fun fetchPokemonList(offset: Int, limit: Int): Result<PokemonListResponse>

    suspend fun fetchPokemonDetails(pokemonId: Int): Result<PokemonResponse>
}