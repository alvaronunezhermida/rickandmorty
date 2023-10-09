package com.rickandmorty.app.data_implementation.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class CharactersResponseDTO(
    @field:Json(name = "info") val info: InfoDTO? = null,
    @field:Json(name = "results") val characters: List<CharacterDTO>? = null
)