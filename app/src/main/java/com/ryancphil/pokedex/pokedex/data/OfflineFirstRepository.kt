package com.ryancphil.pokedex.pokedex.data

import com.ryancphil.pokedex.pokedex.data.database.PokemonEntity
import com.ryancphil.pokedex.pokedex.domain.PokedexRepository
import com.ryancphil.pokedex.pokedex.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
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
    ): Boolean {
        var endReached = false
        apiSource.fetchPokemon(
            offset,
            limit
        )
            .onSuccess {
                Timber.e("Fetch Pokemon Success! $it")
                if (it.pokemonList.isEmpty()) {
                    endReached = true
                } else {
                    val pokemonIdAndNames = it.pokemonList.map { item ->
                        val id = item.url.substringBeforeLast("/").takeLastWhile { it.isDigit() }.toInt()
                        id to item.name.replaceFirstChar { it.uppercase() }
                    }

                    val pokemonList = pokemonIdAndNames.mapIndexed { index, idAndName ->
                        PokemonEntity(
                            id = idAndName.first,
                            name = idAndName.second,
                            spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${idAndName.first}.png"
                        )
                    }
                    withContext(Dispatchers.IO) {
                        roomSource.upsertPokemon(pokemonList)
                    }
                }
            }
            .onFailure {
                Timber.e("Error fetching pokemon: $it")
            }
        return endReached
    }

    override suspend fun fetchPokemonDetails(id: Int): Result<Pokemon> {
        return apiSource.fetchPokemonDetails(id)
            .map {
                it.toPokemon()
            }
    }
}
