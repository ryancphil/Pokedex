package com.ryancphil.pokedex.navigation

sealed class Screen(val route : String) {
    object ScreenPokemonList : Screen("screen_pokemon_list")
    object ScreenPokemonDetails : Screen("screen_pokemon_details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
