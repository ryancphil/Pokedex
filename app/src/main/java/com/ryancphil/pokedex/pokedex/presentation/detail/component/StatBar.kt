package com.ryancphil.pokedex.pokedex.presentation.detail.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryancphil.pokedex.core.presentation.designsystem.theme.AshBlack
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PokedexTheme

@Composable
fun StatBar(
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