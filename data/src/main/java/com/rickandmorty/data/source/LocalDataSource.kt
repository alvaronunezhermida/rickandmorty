package com.rickandmorty.data.source

import arrow.core.Either
import com.rickandmorty.domain.Character
import com.rickandmorty.domain.Empty
import com.rickandmorty.domain.Error
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    val characters: Flow<List<Character>>

    suspend fun isCharactersListEmpty(): Boolean
    suspend fun saveCharacters(characters: List<Character>): Either<Error, Empty>
}