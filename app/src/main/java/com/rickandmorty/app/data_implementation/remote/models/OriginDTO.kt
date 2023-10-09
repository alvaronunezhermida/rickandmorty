package com.rickandmorty.app.data_implementation.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class OriginDTO(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
)