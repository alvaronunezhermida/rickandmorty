package com.rickandmorty.app.data_implementation.local.converters

import androidx.room.TypeConverter
import com.rickandmorty.app.data_implementation.local.entities.LocationEntity
import com.rickandmorty.app.helpers.JsonConverterHelper

class LocationConverter {

    private val jsonConverterHelper: JsonConverterHelper = JsonConverterHelper()

    @TypeConverter
    fun locationToJson(location: LocationEntity): String =
        jsonConverterHelper.toJson(value = location) ?: ""

    @TypeConverter
    fun jsonToLocation(json: String): LocationEntity? =
        jsonConverterHelper.fromJson(json = json)
}