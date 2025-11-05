package com.ryancphil.pokedex.pokedex.presentation.detail

import com.ryancphil.pokedex.pokedex.domain.model.Pokemon

fun Pokemon.toPokemonState(): PokemonState {
    return PokemonState(
        id = id,
        name = name,
        heightInMeters = heightInMeters,
        weightInKilograms = weightInKilograms,
        sprite = sprite,
        types = types,
        baseStats = baseStats.map {
            StatState(
                it.getAbbreviation(),
                it.value
            )
        }
    )
}