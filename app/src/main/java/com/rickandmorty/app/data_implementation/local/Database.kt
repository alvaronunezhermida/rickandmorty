package com.rickandmorty.app.data_implementation.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rickandmorty.app.data_implementation.local.converters.EpisodeConverter
import com.rickandmorty.app.data_implementation.local.converters.LocationConverter
import com.rickandmorty.app.data_implementation.local.converters.OriginConverter
import com.rickandmorty.app.data_implementation.local.daos.CharacterDao
import com.rickandmorty.app.data_implementation.local.entities.CharacterEntity
import com.rickandmorty.app.data_implementation.local.entities.LocationEntity
import com.rickandmorty.app.data_implementation.local.entities.OriginEntity

@Database(
    entities = [
        CharacterEntity::class,
        LocationEntity::class,
        OriginEntity::class
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    LocationConverter::class,
    OriginConverter::class,
    EpisodeConverter::class
)

abstract class Database : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}