package com.ryancphil.pokedex.pokedex.presentation.detail

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.ryancphil.pokedex.R
import com.ryancphil.pokedex.core.presentation.designsystem.theme.AshWhite
import com.ryancphil.pokedex.core.presentation.designsystem.theme.AtkColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.DefColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.HpColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.SpAtkColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.SpDefColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.SpdColor
import com.ryancphil.pokedex.pokedex.domain.model.Type

data class PokemonState(
    val id: Int = -1,
    val name: String = "",
    val heightInMeters: Double = 0.0,
    val weightInKilograms: Double = 0.0,
    val sprite: String = "",
    val types: List<Type> = emptyList(),
    val baseStats: List<StatState> = emptyList()
)

data class StatState(
    val name: String,
    val value: Int
) {
    // TODO: Refactor out magic strings for enum or something
    @StringRes
    fun getContentDescription(): Int = when (name) {
        "HP" -> R.string.hp
        "ATK" -> R.string.attack
        "DEF" -> R.string.defense
        "SP-ATK" -> R.string.special_attack
        "SP-DEF" -> R.string.special_defense
        "SPD" -> R.string.speed
        else -> R.string.unknown
    }
    fun getColor(): Color = when (name) {
        "HP" -> HpColor
        "ATK" -> AtkColor
        "DEF" -> DefColor
        "SP-ATK" -> SpAtkColor
        "SP-DEF" -> SpDefColor
        "SPD" -> SpdColor
        else -> AshWhite
    }
}
