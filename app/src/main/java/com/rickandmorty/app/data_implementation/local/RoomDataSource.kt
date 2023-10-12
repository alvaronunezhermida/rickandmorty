package com.rickandmorty.app.data_implementation.local

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.rickandmorty.app.data_implementation.local.daos.CharacterDao
import com.rickandmorty.data.source.LocalDataSource
import com.rickandmorty.domain.Character
import com.rickandmorty.domain.Empty
import com.rickandmorty.domain.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomDataSource @Inject constructor(private val characterDao: CharacterDao) :
    LocalDataSource {

    override val characters: Flow<List<Character>> =
        characterDao.getAll().map { it.toDomainCharacterList() }

    override suspend fun isCharactersListEmpty(): Boolean = characterDao.charactersCount() == 0

    override suspend fun saveCharacters(characters: List<Character>): Either<Error, Empty> = try {
        characterDao.insertCharacters(characters.toCharacterEntityList())
        Empty().right()
    } catch (e: Exception) {
        Error.Unknown.left()
    }
}