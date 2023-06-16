package com.ryancphil.pokedex.list

import com.ryancphil.pokedex.capitalizeAll
import com.ryancphil.pokedex.data.PokedexRepositoryImpl
import com.ryancphil.pokedex.data.model.PokemonListResponse
import com.ryancphil.pokedex.data.paging.DefaultPaginator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val pageSize = 20

class PokemonListUseCase
@Inject
constructor(
    private val pokedexRepository: PokedexRepositoryImpl
) {

    private val _pokemonList: MutableStateFlow<PokemonListState> =
        MutableStateFlow(PokemonListState())
    val pokemonList: StateFlow<PokemonListState> = _pokemonList.asStateFlow()

    val paginator = DefaultPaginator(
        0,
        onLoadUpdated = {
            _pokemonList.value = _pokemonList.value.copy(isLoading = it)
        },
        onRequest = { nextKey ->
            val result = try {
                val response: PokemonListResponse = pokedexRepository.fetchPokemonList(nextKey, pageSize)!!
                Result.success(response)
            } catch (e: Exception) {
                Result.failure(e)
            }
            result
        },
        getNextKey = { prevKey ->
            prevKey + pageSize
        },
        onError = {
            _pokemonList.value = _pokemonList.value.copy(error = "Check your network and try again.")
        },
        onSuccess = { response, newKey ->
            val names = response.pokemonList.map { it.name }.capitalizeAll()
            _pokemonList.value = _pokemonList.value.copy(
                names = _pokemonList.value.names + names,
                page = newKey,
                error = null,
                endReached = response.count == 0,
            )
        }
    )
}
