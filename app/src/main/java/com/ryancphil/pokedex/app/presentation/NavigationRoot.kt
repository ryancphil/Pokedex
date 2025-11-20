package com.ryancphil.pokedex.app.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ryancphil.pokedex.core.domain.Screen
import com.ryancphil.pokedex.pokedex.presentation.detail.PokemonDetailScreenRoot
import com.ryancphil.pokedex.pokedex.presentation.list.PokemonListScreenRoot
import timber.log.Timber

/**
 * Create the Navigation graph that handles moving between
 * List and Detail screens.
 */
// TODO: Migrate to Nav3
@Composable
fun NavigationRoot(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ScreenPokemonList.route
    ) {
        composable(route = Screen.ScreenPokemonList.route) {
            PokemonListScreenRoot(
                onPokemonClick = {
                    navController.navigate(Screen.ScreenPokemonDetails.withArgs(it.toString()))
                }
            )
        }
        composable(
            route = Screen.ScreenPokemonDetails.route + "/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
            if (pokemonId == null) {
                Timber.e("Navigation Error: pokemonId cannot be null.")
            } else {
                PokemonDetailScreenRoot(
                    onBack = {
                        navController.popBackStack()
                    },
                    pokemonId = pokemonId
                )
            }
        }
    }
}