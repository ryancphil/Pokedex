package com.ryancphil.pokedex.data

import android.util.Log
import com.ryancphil.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.data.model.PokemonResponse
import javax.inject.Inject

class PokedexRepository
@Inject
constructor(
    private val service: PokedexService
) {

    suspend fun fetchPokemonList(
        offset: Int,
        limit: Int
    ): PokemonListResponse? {
        return try {
            val list = service.getPokemonList(
                offset,
                limit
            )
            Log.d("PokedexRepository", "Successfully fetched Pokemon List!")
            return list
        } catch (e: Exception) {
            Log.e("PokedexRepository", "Failed to fetch Pokemon List!")
            null
        }
    }

    suspend fun fetchPokemonDetails(id: Int): PokemonResponse? {
        return try {
            val pokemon = service.getPokemonById(id)
            Log.d("PokedexRepository", "Pokemon Details: $pokemon")
            pokemon
        } catch (e: Exception) {
            Log.e("PokedexRepository", "Failed to fetch Pokemon details for: $id!")
            Log.e("PokedexRepository", "Exception: $e")
            null
        }
    }
}
