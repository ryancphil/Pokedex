package com.ryancphil.pokedex.pokedex.presentation.detail

import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ryancphil.pokedex.core.presentation.designsystem.component.PokedexScaffold
import com.ryancphil.pokedex.core.presentation.designsystem.component.PokedexTopAppBar
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PokedexTheme
import com.ryancphil.pokedex.core.presentation.ui.rememberWindowInfo
import com.ryancphil.pokedex.pokedex.presentation.detail.component.Error
import com.ryancphil.pokedex.pokedex.presentation.detail.component.StatBar
import com.ryancphil.pokedex.pokedex.presentation.detail.component.WeightTypeHeightBar

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
            contentAlignment = Alignment.Center
        ) {
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
                    ORIENTATION_PORTRAIT -> Portrait(state = state.pokemonState)
                    else -> Landscape(state = state.pokemonState)
                }
            }
        }
    }
}

@Composable
fun Portrait(
    state: PokemonState
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
            text = "# ${state.id}",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        // Sprite in the center
        AsyncImage(
            modifier = Modifier.fillMaxWidth(.5f),
            model = state.sprite,
            contentDescription = "Sprite of ${state.name}"
        )

        // Name in the center
        Text(
            text = state.name,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        // HeightTypeWeightBar in the center
        WeightTypeHeightBar(
            weight = "${state.weightInKilograms} kg",
            types = state.types.map { it.toTypeState() },
            height = "${state.heightInMeters} m"
        )

        // Base States Row Items
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            state.baseStats.forEach {
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
fun Landscape(
    state: PokemonState
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 8.dp),
                text = "# ${state.id}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            AsyncImage(
                modifier = Modifier.fillMaxHeight(.5f),
                model = state.sprite,
                contentDescription = "Sprite of ${state.name}"
            )
            Text(
                text = state.name,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            WeightTypeHeightBar(
                modifier = Modifier.padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                weight = "${state.weightInKilograms} kg",
                types = state.types.map { it.toTypeState() },
                height = "${state.heightInMeters} m"
            )
        }

        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                state.baseStats.forEach {
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
}

@PreviewScreenSizes
@Composable
fun PokemonDetailsScreenPreview() {
    PokedexTheme {
        PokemonDetailScreen(
            state = PokemonDetailState(),
            onBack = { })
    }
}

