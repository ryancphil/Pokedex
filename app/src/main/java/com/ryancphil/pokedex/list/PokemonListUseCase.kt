package com.ryancphil.pokedex.list

import com.ryancphil.pokedex.data.PokedexRepositoryImpl
import com.ryancphil.pokedex.list.presentation.PokemonListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val pageSize = 20

@Deprecated(message = "This is not a use case, its a faux viewmodel and needs removal.")
class PokemonListUseCase
@Inject
constructor(
    private val pokedexRepository: PokedexRepositoryImpl
) {

    private val _pokemonList: MutableStateFlow<PokemonListState> =
        MutableStateFlow(PokemonListState())
    val pokemonList: StateFlow<PokemonListState> = _pokemonList.asStateFlow()


}
