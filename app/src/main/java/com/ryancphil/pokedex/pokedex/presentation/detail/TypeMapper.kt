package com.ryancphil.pokedex.pokedex.presentation.detail

import androidx.compose.runtime.Composable
import com.ryancphil.pokedex.core.presentation.designsystem.theme.BugColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.DarkColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.DragonColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.ElectricColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.FairyColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.FightingColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.FireColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.FlyingColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.GhostColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.GrassColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.GroundColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.IceColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.NormalColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PoisonColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.PsychicColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.RockColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.SteelColor
import com.ryancphil.pokedex.core.presentation.designsystem.theme.WaterColor
import com.ryancphil.pokedex.core.presentation.ui.BugIcon
import com.ryancphil.pokedex.core.presentation.ui.DarkIcon
import com.ryancphil.pokedex.core.presentation.ui.DragonIcon
import com.ryancphil.pokedex.core.presentation.ui.ElectricIcon
import com.ryancphil.pokedex.core.presentation.ui.FairyIcon
import com.ryancphil.pokedex.core.presentation.ui.FightingIcon
import com.ryancphil.pokedex.core.presentation.ui.FireIcon
import com.ryancphil.pokedex.core.presentation.ui.FlyingIcon
import com.ryancphil.pokedex.core.presentation.ui.GhostIcon
import com.ryancphil.pokedex.core.presentation.ui.GrassIcon
import com.ryancphil.pokedex.core.presentation.ui.GroundIcon
import com.ryancphil.pokedex.core.presentation.ui.IceIcon
import com.ryancphil.pokedex.core.presentation.ui.NormalIcon
import com.ryancphil.pokedex.core.presentation.ui.PoisonIcon
import com.ryancphil.pokedex.core.presentation.ui.PsychicIcon
import com.ryancphil.pokedex.core.presentation.ui.RockIcon
import com.ryancphil.pokedex.core.presentation.ui.SteelIcon
import com.ryancphil.pokedex.core.presentation.ui.WaterIcon
import com.ryancphil.pokedex.pokedex.domain.model.Type

@Composable
fun Type.toTypeState(): TypeState {
    return when (this) {
        Type.FIGHTING -> TypeState(
            name = this.name,
            icon = FightingIcon,
            tint = FightingColor
        )

        Type.PSYCHIC -> TypeState(
            name = this.name,
            icon = PsychicIcon,
            tint = PsychicColor
        )

        Type.POISON -> TypeState(
            name = this.name,
            icon = PoisonIcon,
            tint = PoisonColor
        )

        Type.DRAGON -> TypeState(
            name = this.name,
            icon = DragonIcon,
            tint = DragonColor
        )

        Type.GHOST -> TypeState(
            name = this.name,
            icon = GhostIcon,
            tint = GhostColor
        )

        Type.DARK -> TypeState(
            name = this.name,
            icon = DarkIcon,
            tint = DarkColor
        )

        Type.GROUND -> TypeState(
            name = this.name,
            icon = GroundIcon,
            tint = GroundColor
        )

        Type.FIRE -> TypeState(
            name = this.name,
            icon = FireIcon,
            tint = FireColor
        )

        Type.FAIRY -> TypeState(
            name = this.name,
            icon = FairyIcon,
            tint = FairyColor
        )

        Type.WATER -> TypeState(
            name = this.name,
            icon = WaterIcon,
            tint = WaterColor
        )

        Type.FLYING -> TypeState(
            name = this.name,
            icon = FlyingIcon,
            tint = FlyingColor
        )

        Type.NORMAL -> TypeState(
            name = this.name,
            icon = NormalIcon,
            tint = NormalColor
        )

        Type.ROCK -> TypeState(
            name = this.name,
            icon = RockIcon,
            tint = RockColor
        )

        Type.ELECTRIC -> TypeState(
            name = this.name,
            icon = ElectricIcon,
            tint = ElectricColor
        )

        Type.BUG -> TypeState(
            name = this.name,
            icon = BugIcon,
            tint = BugColor
        )

        Type.GRASS -> TypeState(
            name = this.name,
            icon = GrassIcon,
            tint = GrassColor
        )

        Type.ICE -> TypeState(
            name = this.name,
            icon = IceIcon,
            tint = IceColor
        )

        Type.STEEL -> TypeState(
            name = this.name,
            icon = SteelIcon,
            tint = SteelColor
        )
    }
}