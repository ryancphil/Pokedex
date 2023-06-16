package com.ryancphil.pokedex.data.repository

import com.ryancphil.pokedex.data.PokedexRepository
import com.ryancphil.pokedex.data.model.NameAndUrlResponse
import com.ryancphil.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.data.model.PokemonResponse
import com.ryancphil.pokedex.data.model.SpritesResponse

class FakePokedexRepository: PokedexRepository {
    override suspend fun fetchPokemonList(offset: Int, limit: Int): PokemonListResponse? {
        return PokemonListResponse(
            5,
            "next_page",
            "previous_page",
            listOf(
                NameAndUrlResponse(name = "", url = ""),
                NameAndUrlResponse(name = "", url = ""),
                NameAndUrlResponse(name = "", url = ""),
                NameAndUrlResponse(name = "", url = ""),
                NameAndUrlResponse(name = "", url = "")
            )
        )
    }

    override suspend fun fetchPokemonDetails(pokemonId: Int): PokemonResponse? {
        if (pokemonId <= 0) {
            return null
        }
        return PokemonResponse(
            name = "Bulbasaur",
            sprites = SpritesResponse(""),
            abilities = emptyList(),
            baseExperience = 50,
            forms = emptyList(),
            gameIndices = emptyList(),
            height = 17,
            heldItems = emptyList(),
            id = 1,
            isDefault = false,
            locationAreaEncounters = null,
            moves = emptyList(),
            order = 1,
            pastTypes = emptyList(),
            species = null,
            stats = emptyList(),
            types = emptyList(),
            weight = 100
        )
    }
}