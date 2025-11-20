package com.ryancphil.pokedex.pokedex.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val spriteUrl: String,
)
