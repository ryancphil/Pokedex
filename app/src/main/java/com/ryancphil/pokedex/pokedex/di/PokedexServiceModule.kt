package com.ryancphil.pokedex.pokedex.di

import com.ryancphil.pokedex.pokedex.data.PokedexService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokedexServiceModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): PokedexService =
        retrofit.create(PokedexService::class.java)
}