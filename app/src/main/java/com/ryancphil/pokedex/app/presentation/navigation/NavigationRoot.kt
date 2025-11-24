package com.ryancphil.pokedex.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.ryancphil.pokedex.pokedex.presentation.detail.PokemonDetailScreenRoot
import com.ryancphil.pokedex.pokedex.presentation.list.PokemonListScreenRoot
import kotlinx.serialization.Serializable

/**
 * Create the Navigation graph in Nav3 that handles moving between
 * Home and Detail screens.
 **/

@Serializable
data object Home : NavKey

@Serializable
data class Details(val pokemonId: Int) : NavKey

@Composable
fun NavigationRoot() {
    val navigationState = rememberNavigationState(
        startRoute = Home,
        topLevelRoutes = setOf(
            Home,
            Details(pokemonId = 0)
        )
    )

    val navigator = remember { Navigator(navigationState) }

    val entryProvider = entryProvider {
        entry<Home> {
            PokemonListScreenRoot(
                onPokemonClick = {
                    navigator.navigate(Details(pokemonId = it))
                }
            )
        }
        entry<Details> {
            PokemonDetailScreenRoot(
                onBack = {
                    navigator.goBack()
                },
                pokemonId = it.pokemonId
            )
        }
    }

    NavDisplay(
        entries = navigationState.toEntries(entryProvider),
        onBack = { navigator.goBack() }
    )
}