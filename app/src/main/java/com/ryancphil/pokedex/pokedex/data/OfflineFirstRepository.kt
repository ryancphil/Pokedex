package com.ryancphil.pokedex.pokedex.data

import com.ryancphil.pokedex.core.domain.capitalizeAll
import com.ryancphil.pokedex.pokedex.data.database.PokemonEntity
import com.ryancphil.pokedex.pokedex.domain.PokedexRepository
import com.ryancphil.pokedex.pokedex.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import javax.inject.Inject

class OfflineFirstRepository
@Inject constructor(
    private val roomSource: LocalDataSource,
    private val apiSource: RemoteDataSource
) : PokedexRepository {

    override fun getCachedPokemon(): Flow<List<Pokemon>> {
        return roomSource.getPokemon()
    }

    override suspend fun fetchPokemon(
        offset: Int,
        limit: Int
    ) {
        apiSource.fetchPokemon(
            offset,
            limit
        )
            .onSuccess {
                Timber.e("Fetch Pokemon Success! $it")
                val pokemonNames = it.pokemonList.map { it.name }
                    .capitalizeAll()
                val pokemonList = pokemonNames.mapIndexed { index, name ->
                    val id = offset + index + 1
                    PokemonEntity(
                        id = id,
                        name = name,
                        spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                    )
                }
                roomSource.upsertPokemon(pokemonList)
            }
            .onFailure {
                Timber.e("Error fetching pokemon: $it")
            }
    }

    override suspend fun fetchPokemonDetails(id: Int): Result<Pokemon> {
        return apiSource.fetchPokemonDetails(id)
            .map {
                it.toPokemon()
            }
    }
}
