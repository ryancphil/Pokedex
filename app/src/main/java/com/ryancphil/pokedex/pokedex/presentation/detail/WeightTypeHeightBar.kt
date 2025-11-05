package com.ryancphil.pokedex.pokedex.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryancphil.pokedex.R
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PokedexTheme
import com.ryancphil.pokedex.pokedex.domain.model.Type

@Composable
fun WeightTypeHeightBar(
    modifier: Modifier = Modifier,
    weight: String,
    types: List<TypeState>,
    height: String
) {
    Row(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f))
            .padding(horizontal = 25.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextItem(
            value = weight,
            title = stringResource(R.string.weight)
        )
        TypeRow(
            types = types
        )
        TextItem(
            value = height,
            title = stringResource(R.string.height)
        )
    }
}


@Composable
private fun TypeRow(
    types: List<TypeState>
) {
    Row() {
        types.forEach {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = it.icon,
                    tint = it.tint,
                    contentDescription = null
                )
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun TextItem(
    value: String,
    title: String,
) { 
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
private fun TextItemPreview() {
    PokedexTheme {
        TextItem(
            value = "12 kg",
            title = "WEIGHT"
        )
    }
}

@Preview
@Composable
private fun WeightTypeHeightPreview() {
    PokedexTheme {
        WeightTypeHeightBar(
            weight = "123 kg",
            types = listOf(
                Type.FIGHTING.toTypeState(),
                Type.FIRE.toTypeState()
            ),
            height = "17 m"
        )
    }
}