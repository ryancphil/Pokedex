package com.ryancphil.pokedex.pokedex.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoveResponse(
    @Json(name = "move") val move: NameAndUrlResponse
)
