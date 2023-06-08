package com.ryancphil.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryancphil.pokedex.detail.PokemonDetailUseCase
import com.ryancphil.pokedex.list.PokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val listUseCase: PokemonListUseCase,
    private val detailUseCase: PokemonDetailUseCase
) : ViewModel() {

    val pokemonList = listUseCase.pokemonList
    val pokemon = detailUseCase.pokemon

    init {
        fetchPokemonList()
    }

    fun fetchPokemonList() {
        viewModelScope.launch(Dispatchers.IO) {
            listUseCase.paginator.loadNextPage()
        }
    }

    fun fetchPokemonDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            detailUseCase.getPokemonDetailViewState(id)
        }
    }

    fun resetDetailLoadingState() {
        detailUseCase.resetLoadingState()
    }

}