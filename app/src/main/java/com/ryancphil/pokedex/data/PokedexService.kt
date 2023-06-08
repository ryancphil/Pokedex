package com.ryancphil.pokedex.data

import com.ryancphil.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit service for Pokédex API: https://pokeapi.co/
 */
interface PokedexService {
    @GET("api/v2/pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListResponse

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): PokemonResponse
}
