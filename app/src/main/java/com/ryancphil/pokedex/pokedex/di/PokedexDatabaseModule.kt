package com.ryancphil.pokedex.pokedex.di

import android.app.Application
import androidx.room.Room
import com.ryancphil.pokedex.pokedex.data.database.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokedexDatabaseModule {

    @Singleton
    @Provides
    fun providePokedexDatabase(applicationContext: Application): PokemonDatabase {
        return Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon_database"
        ).build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.pokemonDao()

}