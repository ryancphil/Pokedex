package com.ryancphil.pokedex.pokedex.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryancphil.pokedex.pokedex.domain.PokedexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel
@Inject constructor(
    private val pokemonRepository: PokedexRepository
) : ViewModel() {

    var state by mutableStateOf(PokemonDetailState())
        private set

    fun getPokemonDetails(id: Int) {
        viewModelScope.launch {
            pokemonRepository.fetchPokemonDetails(id)
                .onSuccess {
                    // TODO: Implement proper formatters and mappers.
                    state = state.copy(
                        isLoading = false,
                        error = null,
                        name = it.name,
                        types = it.types.map { it.type.name }.toString(),
                        sprite = it.sprites.frontDefault,
                        height = it.height.toString(),
                        weight = it.weight.toString(),
                        statsTitle = "Stats",
                        stats = it.stats.map { it.stat.name },
                        abilitiesTitle = "Abilities",
                        abilities = it.abilities.map { it.ability.name },
                        movesTitle = "Moves",
                        moves = it.moves.map { it.move.name }
                    )
                }
                .onFailure {
                    state = state.copy(
                        isLoading = false,
                        error = it.message
                    )
                }
        }
    }

    fun onAction(action: PokemonDetailAction) {
//        when (action) {
//
//        }
    }

}