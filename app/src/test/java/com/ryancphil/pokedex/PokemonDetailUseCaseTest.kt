package com.ryancphil.pokedex

import com.google.common.truth.Truth.assertThat
import com.ryancphil.pokedex.data.repository.FakePokedexRepository
import com.ryancphil.pokedex.detail.PokemonDetailUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokemonDetailUseCaseTest {

    private lateinit var fakePokedexRepository: FakePokedexRepository
    private lateinit var pokemonDetailUseCase: PokemonDetailUseCase

    @Before
    fun setup() {
        fakePokedexRepository = FakePokedexRepository()
        pokemonDetailUseCase = PokemonDetailUseCase(fakePokedexRepository)
    }

    @Test
    fun `loading state is true by default`() {
        assertThat(pokemonDetailUseCase.pokemon.value.isLoading).isTrue()
    }

    @Test
    fun `loading state is false after successful fetchPokemonDetails`() = runBlocking {
        pokemonDetailUseCase.getPokemonDetailViewState(1)
        assertThat(pokemonDetailUseCase.pokemon.value.isLoading).isFalse()
    }

    @Test
    fun `error state contains message after failed fetchPokemonDetails`() = runBlocking {
        pokemonDetailUseCase.getPokemonDetailViewState(-1)
        assertThat(pokemonDetailUseCase.pokemon.value.error).isNotEmpty()
    }


    @Test
    fun `fetchPokemonDetails sets state`() = runBlocking {
        pokemonDetailUseCase.getPokemonDetailViewState(1)
        assertThat(pokemonDetailUseCase.pokemon.value.name).isEqualTo("Bulbasaur")
    }
}
