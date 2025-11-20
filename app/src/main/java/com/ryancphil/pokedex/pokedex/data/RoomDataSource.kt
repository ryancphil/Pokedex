package com.ryancphil.pokedex.pokedex.data

import com.ryancphil.pokedex.pokedex.data.database.PokemonDao
import com.ryancphil.pokedex.pokedex.data.database.PokemonEntity
import com.ryancphil.pokedex.pokedex.data.model.PokemonResponse
import com.ryancphil.pokedex.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class RoomDataSource
@Inject constructor(
    private val pokemonDao: PokemonDao
) : LocalDataSource {
    override fun getPokemon(): Flow<List<Pokemon>> {
        return pokemonDao.getPokemon()
            .map { pokemonList ->
                pokemonList.map {
                    Pokemon(
                        id = it.id,
                        name = it.name,
                        sprite = it.spriteUrl
                    )
                }
            }
    }

    override suspend fun getPokemonById(id: Int): Pokemon? { // TODO
        return null
    }

    override suspend fun upsertPokemon(pokemon: List<PokemonEntity>) {
        Timber.d("Upserting pokemon: $pokemon")
        pokemonDao.insertAll(pokemon)
    }

    override suspend fun upsertPokemon(pokemon: PokemonEntity) { // TODO
    }
}