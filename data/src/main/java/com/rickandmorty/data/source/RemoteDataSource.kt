package com.rickandmorty.data.source

import arrow.core.Either
import com.rickandmorty.domain.CharacterResponse
import com.rickandmorty.domain.Error

interface RemoteDataSource {
    suspend fun getCharacters(): Either<Error, CharacterResponse>
    suspend fun getMoreCharacters(nextUrl: String): Either<Error, CharacterResponse>
}