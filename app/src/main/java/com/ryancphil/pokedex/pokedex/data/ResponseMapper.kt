package com.ryancphil.pokedex.pokedex.data

import com.ryancphil.pokedex.pokedex.data.database.PokemonEntity
import com.ryancphil.pokedex.pokedex.data.model.PokemonResponse
import com.ryancphil.pokedex.pokedex.domain.model.Pokemon
import com.ryancphil.pokedex.pokedex.domain.model.Stat
import com.ryancphil.pokedex.pokedex.domain.model.Type

fun PokemonResponse.toPokemon(): Pokemon {
    return Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        heightInMeters = heightInDecimeters / 10.0,
        weightInKilograms = weightInHectograms / 10.0,
        sprite = sprites.frontDefault,
        types = types.mapNotNull { Type.from(it.type.name) },
        baseStats = stats.map {
            Stat(
                it.stat.name,
                it.baseStat
            )
        }
    )
}

fun PokemonResponse.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        spriteUrl = sprites.frontDefault
    )
}
