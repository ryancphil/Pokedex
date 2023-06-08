package com.ryancphil.pokedex.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ryancphil.pokedex.MainViewModel
import com.ryancphil.pokedex.detail.composable.ScreenPokemonDetails
import com.ryancphil.pokedex.list.composable.ScreenPokemonList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(
    viewModel: MainViewModel
) {
    val navController = rememberNavController()
    val backStackEntryState by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            val currentScreen = backStackEntryState?.destination?.route
            val hasNavIcon = currentScreen != Screen.ScreenPokemonList.route
            PokedexTopAppBar(
                hasNavIcon = hasNavIcon,
                onBackPress = {
                    viewModel.resetDetailLoadingState()
                    navController.popBackStack()
                }
            )
        },
        content = {
            NavHost(
                modifier = Modifier.padding(it),
                navController = navController,
                startDestination = Screen.ScreenPokemonList.route
            ) {
                composable(route = Screen.ScreenPokemonList.route) {
                    ScreenPokemonList(
                        viewModel = viewModel
                    ) { destination ->
                        navController.navigate(destination)
                    }
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
                        Log.e("Navigation", "Error: pokemonId cannot be null.")
                    } else {
                        ScreenPokemonDetails(
                            pokemonId = pokemonId,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    )
}