package com.rickandmorty.app.data_implementation.local.daos

import androidx.room.*
import com.rickandmorty.app.data_implementation.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterEntity")
    fun getAll(): Flow<List<CharacterEntity>>

    @Query("SELECT COUNT(id) FROM CharacterEntity")
    fun breedsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBreeds(breeds: List<CharacterEntity>)

}