package com.ryancphil.pokedex.pokedex.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryancphil.pokedex.core.domain.capitalizeAll
import com.ryancphil.pokedex.pokedex.domain.PokedexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val PAGE_SIZE = 20

@HiltViewModel
class PokemonListViewModel
@Inject constructor(
    private val pokemonRepository: PokedexRepository
) : ViewModel() {

    // Compose state, could also use StateFlow.
    var state by mutableStateOf(PokemonListState())
        private set

    init {
        loadMore()
    }

    fun onAction(action: PokemonListAction) {
        when (action) {
            is PokemonListAction.OnLoadMore -> loadMore()
        }
    }

    private fun loadMore() {
        Timber.d("Loading more...")
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            pokemonRepository.fetchPokemonList(
                offset = PAGE_SIZE * state.currentPage,
                limit = PAGE_SIZE
            )
                .onSuccess { response ->
                    Timber.d("onSuccess Response: $response")
                    val pokemonNames = state.pokemon.map { it.name } + response.pokemonList.map { it.name }.capitalizeAll()
                    val pokemon = pokemonNames.mapIndexed { index, name ->
                        PokemonListItemState(
                            name = name,
                            spriteUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index + 1}.png"
                        )
                    }

                    state = state.copy(
                        isLoading = false,
                        pokemon = pokemon,
                        error = null,
                        endReached = response.count == 0,
                        currentPage = state.currentPage + 1
                    )
                }
                .onFailure {
                    Timber.e("LoadMore Failed: $it")
                    state = state.copy(
                        isLoading = false,
                        error = it.message
                    )
                }
        }
    }
}