package com.ryancphil.pokedex.pokedex.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PokedexTheme

@Composable
fun PokemonRowItem(
    name: String,
    spriteUrl: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = onClick
                )
                .padding(
                    start = 32.dp,
                    end = 32.dp,
                    top = 8.dp,
                    bottom = 8.dp
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(100.dp),
                model = spriteUrl,
                contentDescription = null
            )
            Text(
                modifier = Modifier.weight(1f),
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
private fun PokemonRowItemPreview() {
    PokedexTheme {
        PokemonRowItem(
            name = "Name",
            spriteUrl = "",
            onClick = {})
    }
}

