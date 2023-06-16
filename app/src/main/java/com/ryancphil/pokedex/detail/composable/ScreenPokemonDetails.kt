package com.ryancphil.pokedex.detail.composable

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ryancphil.pokedex.detail.PokemonDetailState
import com.ryancphil.pokedex.rememberWindowInfo

@Composable
fun ScreenPokemonDetails(
    pokemonDetailState: PokemonDetailState
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (pokemonDetailState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .align(Alignment.Center)
            )
        } else if (pokemonDetailState.error != null) {
            Error(error = pokemonDetailState.error)
        } else {
            val windowInfo = rememberWindowInfo()
            when (windowInfo.orientation) {
                ORIENTATION_PORTRAIT -> Portrait(state = pokemonDetailState)
                else -> Landscape(state = pokemonDetailState)
            }
        }
    }
}

@Composable
fun Error(
    error: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        BannerText(
            text = error.toString(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Portrait(
    state: PokemonDetailState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DetailHeader(
            state.name,
            state.types
        )
        FramedSprite(
            name = state.name,
            sprite = state.sprite
        )
        SizeBanner(
            state.height,
            state.weight
        )
        LazyColumn {
            items(1) {
                SectionHeader(sectionTitle = state.statsTitle)
                SectionItems(items = state.stats)

                SectionHeader(sectionTitle = state.abilitiesTitle)
                SectionItems(items = state.abilities)

                SectionHeader(sectionTitle = state.movesTitle)
                SectionItems(items = state.moves)
            }
        }
    }
}

@Composable
fun Landscape(
    state: PokemonDetailState
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .weight(1f)
                .padding(
                    start = 24.dp,
                    top = 24.dp,
                    end = 12.dp,
                    bottom = 24.dp,
                )
        ) {
            DetailHeader(
                state.name,
                state.types
            )
            FramedSprite(
                name = state.name,
                sprite = state.sprite
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .weight(1f)
                .padding(
                    start = 12.dp,
                    top = 24.dp,
                    end = 24.dp,
                    bottom = 24.dp,
                )
        ) {
            SizeBanner(
                state.height,
                state.weight
            )
            LazyColumn {
                items(1) {
                    SectionHeader(sectionTitle = state.statsTitle)
                    SectionItems(items = state.stats)

                    SectionHeader(sectionTitle = state.abilitiesTitle)
                    SectionItems(items = state.abilities)

                    SectionHeader(sectionTitle = state.movesTitle)
                    SectionItems(items = state.moves)
                }
            }
        }
    }
}

@Composable
@Preview
fun DetailHeader(
    name: String = "Pokemon",
    types: String = "types"
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )
        Text(
            text = types
        )
    }
}

@Composable
@Preview
fun FramedSprite(
    name: String = "pokemon",
    sprite: String = ""
) {
    val colorStops = arrayOf(
        0.01f to MaterialTheme.colorScheme.background,
        0.99f to MaterialTheme.colorScheme.secondary
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Brush.linearGradient(colorStops = colorStops))
            .border(8.dp, MaterialTheme.colorScheme.tertiary)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth(.5f)
                .align(Alignment.Center),
            model = sprite,
            contentDescription = "Sprite of $name"
        )
    }
}

@Composable
@Preview
fun SizeBanner(
    height: String = "0",
    weight: String = "0",
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.secondary)
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BannerText(text = "Height: $height\"")
            Spacer(modifier = Modifier.width(4.dp))
            BannerText(text = "Weight: $weight lbs")
        }
    }
}

@Composable
fun BannerText(
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        textAlign = textAlign,
        text = text,
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun TitleText(
    modifier: Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun BodyText(
    modifier: Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp
    )
}

@Composable
fun SectionHeader(
    sectionTitle: String
) {
    Column {
        TitleText(
            modifier = Modifier.fillMaxWidth(),
            text = sectionTitle
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(MaterialTheme.colorScheme.secondary)
        )
    }
}

@Composable
fun SectionItems(
    items: List<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        items.forEach { item ->
            BodyText(
                modifier = Modifier.padding(start = 8.dp),
                text = item
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.secondary)
            )
        }
    }
}