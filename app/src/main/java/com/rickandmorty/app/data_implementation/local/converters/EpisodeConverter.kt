package com.rickandmorty.app.data_implementation.local.converters

import androidx.room.TypeConverter
import com.rickandmorty.app.helpers.JsonConverterHelper

class EpisodeConverter {

    private val jsonConverterHelper: JsonConverterHelper = JsonConverterHelper()

    @TypeConverter
    fun originToJson(origin: List<String>): String =
        jsonConverterHelper.toListJson(value = origin) ?: ""

    @TypeConverter
    fun jsonToOrigin(json: String): List<String> =
        jsonConverterHelper.fromListJson(json = json) ?: emptyList()
}