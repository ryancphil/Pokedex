package com.ryancphil.pokedex.list.composable

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryancphil.pokedex.list.PokemonListState

@Composable
fun ScreenPokemonList(
    pokemonListState: PokemonListState,
    loadPage: () -> Unit,
    onClick: (Int) -> Unit
) {
    LazyColumn {
        itemsIndexed(
            items = pokemonListState.names
        ) { index, pokemonName ->
            if (
                index >= pokemonListState.names.size - 1 &&
                !pokemonListState.endReached &&
                !pokemonListState.isLoading
            ) {
                loadPage()
            }
            PokemonRowItem(name = pokemonName) {
                val id = index + 1
                onClick(id)
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(MaterialTheme.colorScheme.tertiary)
            )
        }
        item {
            if (pokemonListState.isLoading) {
                LoadingIndicator()
            } else if (pokemonListState.error != null) {
                ErrorRefreshItem(
                    errorMessage = pokemonListState.error.toString(),
                    onRefresh = {
                        loadPage()
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