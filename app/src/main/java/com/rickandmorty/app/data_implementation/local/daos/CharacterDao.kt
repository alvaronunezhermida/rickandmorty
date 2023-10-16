package com.rickandmorty.app.data_implementation.local.daos

import androidx.room.*
import com.rickandmorty.app.data_implementation.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterEntity")
    fun getAll(): Flow<List<CharacterEntity>>
    @Query("SELECT * FROM CharacterEntity WHERE id = :characterId")
    fun getCharacter(characterId: Int): CharacterEntity

    @Query("SELECT COUNT(id) FROM CharacterEntity")
    fun charactersCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCharacters(characters: List<CharacterEntity>)

}