package com.ryancphil.pokedex.pokedex.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryancphil.pokedex.pokedex.domain.PokedexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val PAGE_SIZE = 20

@HiltViewModel
class PokemonListViewModel
@Inject constructor(
    private val pokemonRepository: PokedexRepository
) : ViewModel() {

    var state by mutableStateOf(PokemonListState())
        private set

    init {
        loadFromDatabase()
    }

    fun onAction(action: PokemonListAction) {
        when (action) {
            is PokemonListAction.OnLoadMore -> loadMore()
        }
    }

    private fun loadFromDatabase() {
        pokemonRepository.getCachedPokemon()
            .onEach { list ->
                if (list.isEmpty()) {
                    Timber.d("INITIAL LOAD, SHOULD ONLY FIRE ONCE!")
                    loadMore()
                }
                val pokemon = list.map {
                    PokemonListItemState(
                        id = it.id,
                        name = it.name,
                        spriteUrl = it.sprite
                    )
                }
                state = state.copy(pokemon = pokemon)
            }
            .launchIn(viewModelScope)
    }

    private fun loadMore() {
        viewModelScope.launch {
            if (!state.isLoading && !state.endReached) {
                state = state.copy(isLoading = true)
                Timber.d("Loading page: ${state.currentPage}")
                val endReached = pokemonRepository.fetchPokemon(
                    offset = PAGE_SIZE * state.currentPage,
                    limit = PAGE_SIZE
                )
                state = state.copy(
                    isLoading = false,
                    currentPage = state.currentPage + 1,
                    endReached = endReached
                )
            }
        }
    }
}