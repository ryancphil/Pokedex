package com.ryancphil.pokedex.pokedex.data

import com.ryancphil.pokedex.pokedex.data.database.PokemonEntity
import com.ryancphil.pokedex.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getPokemon(): Flow<List<Pokemon>>
    suspend fun getPokemonById(id: Int): Pokemon?
    suspend fun upsertPokemon(pokemon: List<PokemonEntity>)
    suspend fun upsertPokemon(pokemon: PokemonEntity)
}