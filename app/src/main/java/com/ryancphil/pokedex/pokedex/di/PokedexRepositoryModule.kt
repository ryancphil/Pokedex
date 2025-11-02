package com.ryancphil.pokedex.pokedex.di

import com.ryancphil.pokedex.pokedex.data.PokedexRepositoryImpl
import com.ryancphil.pokedex.pokedex.domain.PokedexRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokedexRepositoryModule {

    @Binds
    abstract fun bindPokedexRepository(pokedexRepositoryImpl: PokedexRepositoryImpl): PokedexRepository
}