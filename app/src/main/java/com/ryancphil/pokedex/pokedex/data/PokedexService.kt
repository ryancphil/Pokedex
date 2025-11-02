package com.ryancphil.pokedex.pokedex.data

import com.ryancphil.pokedex.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.pokedex.data.model.PokemonResponse
import retrofit2.Response
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
    ): Response<PokemonListResponse>

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): Response<PokemonResponse>
}
