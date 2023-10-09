package com.rickandmorty.app.data_implementation.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rickandmorty.app.data_implementation.local.daos.CharacterDao
import com.rickandmorty.app.data_implementation.local.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun breedDao(): CharacterDao
}