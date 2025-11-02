package com.ryancphil.pokedex.pokedex.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse(
    @Json(name = "name") val name: String,
    @Json(name = "sprites") val sprites: SpritesResponse,
    @Json(name = "abilities") val abilities: List<AbilityResponse>,
    @Json(name = "base_experience") val baseExperience: Int,
    @Json(name = "forms") val forms: List<NameAndUrlResponse>,
    @Json(name = "game_indices") val gameIndices: List<Any>,
    @Json(name = "height") val height: Int,
    @Json(name = "held_items") val heldItems: List<Any?>,
    @Json(name = "id") val id: Int,
    @Json(name = "is_default") val isDefault: Boolean,
    @Json(name = "location_area_encounters") val locationAreaEncounters: String?,
    @Json(name = "moves") val moves: List<MoveResponse>,
    @Json(name = "order") val order: Int,
    @Json(name = "past_types") val pastTypes: List<Any?>,
    @Json(name = "species") val species: Any?,
    @Json(name = "stats") val stats: List<StatResponse>,
    @Json(name = "types") val types: List<TypeResponse>,
    @Json(name = "weight") val weight: Int
)
