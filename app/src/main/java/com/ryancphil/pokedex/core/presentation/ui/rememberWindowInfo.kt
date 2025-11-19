package com.ryancphil.pokedex.core.presentation.ui

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.content.res.Configuration.ORIENTATION_PORTRAIT
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun rememberWindowInfo(): WindowInfo {
    val configuration = LocalWindowInfo.current
    return WindowInfo(
        screenWidthInfo = when {
            configuration.containerSize.width < 600 -> WindowInfo.WindowType.Compact
            configuration.containerSize.width < 840 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenHeightInfo =when {
            configuration.containerSize.height < 480 -> WindowInfo.WindowType.Compact
            configuration.containerSize.height < 900 -> WindowInfo.WindowType.Medium
            else -> WindowInfo.WindowType.Expanded
        },
        screenWidth = configuration.containerSize.width.dp,
        screenHeight = configuration.containerSize.height.dp,
        orientation = if (configuration.containerSize.height.dp > configuration.containerSize.width.dp) {
            ORIENTATION_PORTRAIT
        } else {
            ORIENTATION_LANDSCAPE
        }
    )
}

data class WindowInfo(
    val screenWidthInfo: WindowType,
    val screenHeightInfo: WindowType,
    val screenWidth: Dp,
    val screenHeight: Dp,
    val orientation: Int
) {
    sealed class WindowType {
        object Compact : WindowType()
        object Medium : WindowType()
        object Expanded : WindowType()
    }
}