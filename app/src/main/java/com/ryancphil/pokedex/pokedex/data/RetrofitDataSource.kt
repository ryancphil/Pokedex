package com.ryancphil.pokedex.pokedex.data

import com.ryancphil.pokedex.core.data.safeCall
import com.ryancphil.pokedex.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.pokedex.data.model.PokemonResponse
import javax.inject.Inject

class RetrofitDataSource
@Inject constructor(
    private val service: PokedexService
) : RemoteDataSource {

    override suspend fun fetchPokemon(
        offset: Int,
        limit: Int
    ): Result<PokemonListResponse> {
        return safeCall {
            service.getPokemonList(
                offset,
                limit
            )
        }
    }

    override suspend fun fetchPokemonDetails(id: Int): Result<PokemonResponse> {
        return safeCall {
            service.getPokemonById(id)
        }
    }

}