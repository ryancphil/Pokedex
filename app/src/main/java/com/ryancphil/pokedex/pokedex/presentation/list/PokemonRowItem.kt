package com.ryancphil.pokedex.pokedex.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PokedexTheme

@Composable
fun PokemonRowItem(
    pokemonOne: PokemonListItemState,
    pokemonTwo: PokemonListItemState? = null,
    onAction: (PokemonListAction) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PokemonItem(
            modifier = Modifier.weight(1f),
            id = pokemonOne.id.toString(),
            name = pokemonOne.name,
            spriteUrl = pokemonOne.spriteUrl,
            onClick = {
                onAction(PokemonListAction.OnPokemonClick(pokemonOne.id))
            })
        Spacer(modifier = Modifier.width(4.dp))
        if (pokemonTwo != null) {
            PokemonItem(
                modifier = Modifier.weight(1f),
                id = pokemonTwo.id.toString(),
                name = pokemonTwo.name,
                spriteUrl = pokemonTwo.spriteUrl,
                onClick = {
                    onAction(PokemonListAction.OnPokemonClick(pokemonTwo.id))
                })
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    id: String,
    name: String,
    spriteUrl: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .size(200.dp)
            .padding(4.dp)
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
            .clickable(
                onClick = onClick
            )
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "#$id"
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = spriteUrl,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                textAlign = TextAlign.Center,
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
private fun PokemonItemPreview() {
    PokedexTheme {
        PokemonItem(
            id = "1",
            name = "Bulbasaur",
            spriteUrl = "",
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun PokemonRowItemPreview() {
    PokedexTheme {
        PokemonRowItem(
            pokemonOne = PokemonListItemState(),
            pokemonTwo = PokemonListItemState(),
            onAction = {}
        )
    }
}

