package com.ryancphil.pokedex.pokedex.di

import com.ryancphil.pokedex.pokedex.data.LocalDataSource
import com.ryancphil.pokedex.pokedex.data.OfflineFirstRepository
import com.ryancphil.pokedex.pokedex.data.RemoteDataSource
import com.ryancphil.pokedex.pokedex.data.RetrofitDataSource
import com.ryancphil.pokedex.pokedex.data.RoomDataSource
import com.ryancphil.pokedex.pokedex.domain.PokedexRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokedexRepositoryModule {

    @Binds
    abstract fun bindLocalDataSource(roomDataSource: RoomDataSource): LocalDataSource
    
    @Binds
    abstract fun bindRemoteDataSource(retrofitDataSource: RetrofitDataSource): RemoteDataSource
    
    @Binds
    abstract fun bindPokedexRepository(offlineFirstRepository: OfflineFirstRepository): PokedexRepository
}