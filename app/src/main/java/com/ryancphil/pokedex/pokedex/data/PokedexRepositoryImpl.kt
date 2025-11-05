package com.ryancphil.pokedex.pokedex.data

import com.ryancphil.pokedex.core.data.safeCall
import com.ryancphil.pokedex.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.pokedex.domain.PokedexRepository
import com.ryancphil.pokedex.pokedex.domain.model.Pokemon
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

    override suspend fun fetchPokemonDetails(pokemonId: Int): Result<Pokemon> {
        return safeCall {
            service.getPokemonById(pokemonId)
        }.map {
            it.toPokemon()
        }

    }
}
