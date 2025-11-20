package com.ryancphil.pokedex.pokedex.presentation.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ryancphil.pokedex.core.presentation.designsystem.component.PokedexScaffold
import com.ryancphil.pokedex.core.presentation.designsystem.component.PokedexTopAppBar
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PokedexTheme
import timber.log.Timber

@Composable
fun PokemonListScreenRoot(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onPokemonClick: (Int) -> Unit
) {
    PokemonListScreen(
        state = viewModel.state,
        onAction = { action ->
            // Pass navigation side-effects up to NavigationRoot
            when (action) {
                is PokemonListAction.OnPokemonClick -> onPokemonClick(action.id)
            }
            // Process all actions in the viewModel
            viewModel.onAction(action)
        }
    )

}

@Composable
fun PokemonListScreen(
    state: PokemonListState,
    onAction: (PokemonListAction) -> Unit
) {
    /**
     * Trigger loading next page using Compose's lazyListState
     */
    val lazyListState = rememberLazyListState()
    LaunchedEffect(lazyListState) {
        snapshotFlow {
            !lazyListState.canScrollForward &&
                    !state.isLoading &&
                    !state.endReached
        }.collect { shouldLoad ->
            if (shouldLoad) {
                Timber.d("Scrolled to bottom!")
                onAction(PokemonListAction.OnLoadMore)
            }
        }
    }

    PokedexScaffold(
        topBar = {
            PokedexTopAppBar(
                hasNavIcon = false,
                onBackPress = {}
            )
        }
    ) { padding ->
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            items(
                count = state.pokemon.size / 2,
            ) { index ->
                PokemonRowItem(
                    pokemonOne = state.pokemon[index * 2],
                    pokemonTwo = state.pokemon[index * 2 + 1],
                    onAction = onAction
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
