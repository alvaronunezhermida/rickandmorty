package com.rickandmorty.app.data_implementation.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = false)
data class InfoDTO(
    @field:Json(name = "count") val count: Int? = null,
    @field:Json(name = "pages") val pages: Int? = null,
    @field:Json(name = "next") val next: String? = null,
    @field:Json(name = "prev") val prev: String? = null
)