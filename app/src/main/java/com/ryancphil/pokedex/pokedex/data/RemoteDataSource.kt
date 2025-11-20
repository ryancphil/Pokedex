package com.ryancphil.pokedex.pokedex.data

import com.ryancphil.pokedex.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.pokedex.data.model.PokemonResponse

interface RemoteDataSource {
    suspend fun fetchPokemon(offset: Int, limit: Int): Result<PokemonListResponse>
    suspend fun fetchPokemonDetails(id: Int): Result<PokemonResponse>
}