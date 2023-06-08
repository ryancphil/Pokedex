package com.ryancphil.pokedex.navigation

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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
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
                text = "Pokédex"
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