package com.rickandmorty.data.source

import arrow.core.Either
import com.rickandmorty.domain.Character
import com.rickandmorty.domain.Error

interface RemoteDataSource {
    suspend fun getCharacters(): Either<Error, List<Character>>
}