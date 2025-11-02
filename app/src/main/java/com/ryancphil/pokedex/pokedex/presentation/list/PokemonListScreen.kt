package com.ryancphil.pokedex.pokedex.presentation.list

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ryancphil.pokedex.core.presentation.designsystem.component.PokedexTopAppBar
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PokedexTheme

@Composable
fun PokemonListScreenRoot(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onPokemonClick: (Int) -> Unit
) {
    PokemonListScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is PokemonListAction.OnPokemonClick -> onPokemonClick(action.id)
            }
            viewModel.onAction(action)
        }
    )

}

@Composable
fun PokemonListScreen(
    state: PokemonListState,
    onAction: (PokemonListAction) -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            PokedexTopAppBar(
                hasNavIcon = false,
                onBackPress = {}
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            items(
                count = state.pokemon.size,
            ) { index ->
                if (index >= state.pokemon.size - 1 &&
                    !state.isLoading &&
                    !state.endReached) {
                    // We have scrolled to the bottom.
                    onAction(PokemonListAction.OnLoadMore)
                }
                PokemonRowItem(
                    name = state.pokemon[index].first,
                    spriteUrl = state.pokemon[index].second,
                    onClick = {
                        onAction(PokemonListAction.OnPokemonClick(index + 1))
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PokemonListScreenPreview() {
    PokedexTheme {
        PokemonListScreen(
            state = PokemonListState(),
            onAction = {}
        )
    }
}
