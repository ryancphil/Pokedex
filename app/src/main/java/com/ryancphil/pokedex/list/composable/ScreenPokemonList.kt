package com.ryancphil.pokedex.list.composable

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ryancphil.pokedex.MainViewModel
import com.ryancphil.pokedex.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun ScreenPokemonList(
    viewModel: MainViewModel,
    openDetail: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val pokemonListViewState by viewModel.pokemonList.collectAsStateWithLifecycle()
    LazyColumn {
        itemsIndexed(
            items = pokemonListViewState.names
        ) { index, pokemonName ->
            if (index >= pokemonListViewState.names.size - 1 && !pokemonListViewState.endReached && !pokemonListViewState.isLoading) {
                LaunchedEffect(key1 = "list", block = {
                    viewModel.fetchPokemonList()
                })
            }
            PokemonRowItem(name = pokemonName) {
                val id = index + 1
                val destination = Screen.ScreenPokemonDetails.withArgs(id.toString())
                Log.d("ScreenPokemonList", "Destination: $destination")
                openDetail(destination)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.tertiary)
            )
        }
        item {
            if (pokemonListViewState.isLoading) {
                LoadingIndicator()
            } else if (pokemonListViewState.error != null) {
                ErrorRefreshItem(
                    errorMessage = pokemonListViewState.error.toString(),
                    onRefresh = {
                        scope.launch {
                            viewModel.fetchPokemonList()
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview
fun LoadingIndicator() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
@Preview
fun ErrorRefreshItem(
    errorMessage: String = "Error",
    onRefresh: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = errorMessage)
        Image(
            modifier = Modifier.clickable {
                onRefresh()
            },
            imageVector = Icons.Default.Refresh,
            contentDescription = "Refresh Icon"
        )
    }
}