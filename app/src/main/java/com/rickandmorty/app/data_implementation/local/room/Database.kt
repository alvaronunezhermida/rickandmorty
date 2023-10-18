package com.rickandmorty.app.data_implementation.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rickandmorty.app.data_implementation.local.room.converters.EpisodeConverter
import com.rickandmorty.app.data_implementation.local.room.converters.LocationConverter
import com.rickandmorty.app.data_implementation.local.room.converters.OriginConverter
import com.rickandmorty.app.data_implementation.local.room.daos.CharacterDao
import com.rickandmorty.app.data_implementation.local.room.entities.CharacterEntity
import com.rickandmorty.app.data_implementation.local.room.entities.LocationEntity
import com.rickandmorty.app.data_implementation.local.room.entities.OriginEntity

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