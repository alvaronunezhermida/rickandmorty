package com.rickandmorty.app.data_implementation.local.converters

import androidx.room.TypeConverter
import com.rickandmorty.app.data_implementation.local.entities.OriginEntity
import com.rickandmorty.app.helpers.JsonConverterHelper

class OriginConverter {

    private val jsonConverterHelper: JsonConverterHelper = JsonConverterHelper()

    @TypeConverter
    fun originToJson(origin: OriginEntity): String =
        jsonConverterHelper.toJson(value = origin) ?: ""

    @TypeConverter
    fun jsonToOrigin(json: String): OriginEntity? =
        jsonConverterHelper.fromJson(json = json)
}