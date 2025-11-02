package com.ryancphil.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ryancphil.pokedex.list.presentation.PokemonListScreenRoot

/**
 * Create the Navigation graph that handles moving between
 * List and Detail screens.
 *
 * Use the compscreen live template to create new screens.
 */
@Composable
fun NavigationRoot(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ScreenPokemonList.route
    ) {
        composable(route = Screen.ScreenPokemonList.route) {
            PokemonListScreenRoot()
        }
        composable(
            route = Screen.ScreenPokemonDetails.route + "/{pokemonId}",
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
//            val pokemonId = backStackEntry.arguments?.getInt("pokemonId")
//            if (pokemonId == null) {
//                Log.e("Navigation", "Error: pokemonId cannot be null.")
//            } else {
//                ScreenPokemonDetails(
//                    pokemonDetailState = pokemonDetailState
//                )
//            }
        }
    }
}