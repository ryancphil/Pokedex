package com.ryancphil.pokedex.pokedex.domain

import com.ryancphil.pokedex.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    fun getCachedPokemon(): Flow<List<Pokemon>>
    suspend fun fetchPokemon(offset: Int, limit: Int)
    suspend fun fetchPokemonDetails(id: Int): Result<Pokemon>
}