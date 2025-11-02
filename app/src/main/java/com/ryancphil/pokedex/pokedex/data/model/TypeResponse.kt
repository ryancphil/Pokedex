package com.ryancphil.pokedex.pokedex.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeResponse(
    @Json(name = "slot") val slot: Int,
    @Json(name = "type") val type: NameAndUrlResponse
)
