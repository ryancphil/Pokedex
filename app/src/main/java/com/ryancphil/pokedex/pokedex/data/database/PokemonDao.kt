package com.ryancphil.pokedex.pokedex.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemon_table")
    fun getPokemon(): Flow<List<PokemonEntity>>

    @Upsert
    suspend fun insertAll(pokemon: List<PokemonEntity>)
}