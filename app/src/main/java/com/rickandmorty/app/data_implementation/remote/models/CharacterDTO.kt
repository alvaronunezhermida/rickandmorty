package com.rickandmorty.app.data_implementation.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class CharacterDTO(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "status") val status: String,
    @field:Json(name = "species") val species: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "gender") val gender: String,
    @field:Json(name = "origin") val origin: OriginDTO,
    @field:Json(name = "location") val location: LocationDTO,
    @field:Json(name = "image") val image: String,
    @field:Json(name = "episode") val episode: List<String>,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "created") val created: String
)