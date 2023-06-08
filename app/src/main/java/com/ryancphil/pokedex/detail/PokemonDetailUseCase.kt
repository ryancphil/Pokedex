package com.ryancphil.pokedex.detail

import com.ryancphil.pokedex.data.PokedexRepository
import com.ryancphil.pokedex.data.model.PokemonResponse
import com.ryancphil.pokedex.data.model.StatResponse
import com.ryancphil.pokedex.data.model.TypeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PokemonDetailUseCase
@Inject
constructor(
    private val pokedexRepository: PokedexRepository
) {

    private val _pokemon: MutableStateFlow<PokemonDetailViewState> = MutableStateFlow(PokemonDetailViewState())
    val pokemon: StateFlow<PokemonDetailViewState> = _pokemon.asStateFlow()

    suspend fun getPokemonDetailViewState(id: Int) {
        val detailResponse = pokedexRepository.fetchPokemonDetails(id)
        if (detailResponse == null) {
            _pokemon.value = _pokemon.value.copy(
                isLoading = false,
                error = "Huh. It seems like this Pokédex is acting funny... check your connection or try again later."
            )
        } else {
            _pokemon.value = _pokemon.value.copy(
                isLoading = false,
                error = null,
                name = detailResponse.name,
                types = createTypesString(detailResponse.types),
                sprite = detailResponse.sprites.frontDefault,
                height = detailResponse.height.toString(),
                weight = detailResponse.weight.toString(),
                statsTitle = "Stats",
                stats = createStatsList(detailResponse.stats),
                abilitiesTitle = "Abilities",
                abilities = detailResponse.abilities.map { it.ability.name },
                movesTitle = "Moves",
                moves = detailResponse.moves.map { it.move.name }
            )
        }
    }

    fun resetLoadingState() {
        _pokemon.value = _pokemon.value.copy(
            isLoading = true,
            error = null
        )
    }

    private fun createTypesString(types: List<TypeResponse>): String {
        return buildString {
            types.forEach {
                append("${it.type.name} ")
            }
        }
    }

    private fun createStatsList(stats: List<StatResponse>): List<String> {
        return stats.map { "${it.stat.name}:  ${it.baseStat}" }
    }
}