package com.ryancphil.pokedex.di

import com.ryancphil.pokedex.data.PokedexRepository
import com.ryancphil.pokedex.data.PokedexRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokedexModule {

    @Binds
    abstract fun bindPokedexRepository(pokedexRepositoryImpl: PokedexRepositoryImpl): PokedexRepository
}