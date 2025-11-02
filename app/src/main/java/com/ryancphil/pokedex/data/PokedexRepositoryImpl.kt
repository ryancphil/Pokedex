package com.ryancphil.pokedex.data

import com.ryancphil.pokedex.core.data.safeCall
import com.ryancphil.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.data.model.PokemonResponse
import com.ryancphil.pokedex.domain.PokedexRepository
import javax.inject.Inject

class PokedexRepositoryImpl
@Inject constructor(
    private val service: PokedexService
) : PokedexRepository {

    override suspend fun fetchPokemonList(
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

    override suspend fun fetchPokemonDetails(pokemonId: Int): Result<PokemonResponse> {
        return safeCall {
            service.getPokemonById(pokemonId)
        }
    }
}
