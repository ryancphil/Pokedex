package com.ryancphil.pokedex.pokedex.presentation.detail

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ryancphil.pokedex.core.presentation.designsystem.component.PokedexScaffold
import com.ryancphil.pokedex.core.presentation.designsystem.component.PokedexTopAppBar
import com.ryancphil.pokedex.core.presentation.designsystem.theme.AshBlack
import com.ryancphil.pokedex.core.presentation.designsystem.theme.AshWhite
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PokedexTheme
import com.ryancphil.pokedex.core.presentation.ui.rememberWindowInfo
import timber.log.Timber

@Composable
fun PokemonDetailScreenRoot(
    onBack: () -> Unit,
    pokemonId: Int,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    viewModel.getPokemonDetails(pokemonId)
    PokemonDetailScreen(
        state = viewModel.state,
        onBack = onBack
    )
}

@Composable
fun PokemonDetailScreen(
    state: PokemonDetailState,
    onBack: () -> Unit
) {
    PokedexScaffold(
        topBar = {
            PokedexTopAppBar(
                hasNavIcon = true,
                onBackPress = {
                    onBack()
                })
        }) { padding ->

        val types = state.pokemonState.types
        val colors = buildList {
            add(MaterialTheme.colorScheme.background)
            types.map { add(it.toTypeState().tint.copy(alpha = 0.3f)) }
            add(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f))

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = colors
                    )
                )
                .padding(padding),
            contentAlignment = Alignment.Center) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .align(Alignment.Center)
                )
            } else if (state.error != null) {
                Error(error = state.error)
            } else {
                val windowInfo = rememberWindowInfo()
                when (windowInfo.orientation) {
                    ORIENTATION_PORTRAIT -> Portrait(pokemonState = state.pokemonState)
                    else -> Landscape(state = state)
                }
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
        Text(
            text = error.toString(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Portrait(
    pokemonState: PokemonState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) { // Number in the top left
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "# ${pokemonState.id}",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        // Sprite in the center
        AsyncImage(
            modifier = Modifier.fillMaxWidth(.5f),
            model = pokemonState.sprite,
            contentDescription = "Sprite of ${pokemonState.name}"
        )

        // Name in the center
        Text(
            text = pokemonState.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        // HeightTypeWeightBar in the center
        WeightTypeHeightBar(
            weight = "${pokemonState.weightInKilograms} kg",
            types = pokemonState.types.map { it.toTypeState() },
            height = "${pokemonState.heightInMeters} m"
        )

        // Base States Row Items
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            pokemonState.baseStats.forEach {
                StatBar(
                    modifier = Modifier.padding(2.dp),
                    name = it.name,
                    value = it.value,
                    color = it.getColor()
                )
            }
        }
    }
}

@Composable
private fun StatBar(
    modifier: Modifier = Modifier,
    name: String,
    value: Int,
    color: Color
) {
    val animatableFraction = remember { Animatable(0f) }
    LaunchedEffect(value) {
        animatableFraction.animateTo(
            targetValue = maxOf(
                value / 100f,
                0.3f
            ),
            animationSpec = tween(
                1000,
                easing = FastOutSlowInEasing
            )
        )
    } // Background Pill
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100))
            .background(MaterialTheme.colorScheme.background)
    ) { // Fill Pill
        Box(
            modifier = modifier
                .fillMaxWidth(animatableFraction.value)
                .clip(RoundedCornerShape(100))
                .background(color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Text(
                    text = value.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.background,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
private fun StatBarPreview() {
    PokedexTheme {
        StatBar(
            name = "SP-ATK",
            value = 50,
            color = AshBlack
        )
    }
}

@Composable
fun Landscape(
    state: PokemonDetailState
) { // TODO
}

