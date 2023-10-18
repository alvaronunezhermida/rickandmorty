package com.rickandmorty.app.data_implementation.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OriginEntity(
    @PrimaryKey(autoGenerate = false) val name: String,
    val url: String
)