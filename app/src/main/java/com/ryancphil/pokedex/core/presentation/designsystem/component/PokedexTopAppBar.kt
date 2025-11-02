@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryancphil.pokedex.core.presentation.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryancphil.pokedex.R
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PokedexTheme

// TODO: Customize to look like the top of an actual Pokedex
@Composable
fun PokedexTopAppBar(
    hasNavIcon: Boolean,
    onBackPress: () -> Unit,
) {
    CenterAlignedTopAppBar(
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        title = {
            Text(
                modifier = Modifier.padding(4.dp),
                text = stringResource(R.string.pokedex)
            )
        },
        navigationIcon = {
            if (hasNavIcon) {
                Icon(
                    modifier = Modifier.clickable {
                        onBackPress()
                    },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back arrow",
                )
            }
        }
    )
}

@Preview
@Composable
private fun PokedexTopAppBarPreview() {
    PokedexTheme {
        PokedexTopAppBar(
            hasNavIcon = true,
            onBackPress = { }
        )
    }
}