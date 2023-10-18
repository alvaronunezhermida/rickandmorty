package com.rickandmorty.data.source

import arrow.core.Either
import com.rickandmorty.domain.Empty
import com.rickandmorty.domain.Error

interface PreferencesDataSource {
    suspend fun saveNextUrl(nextUrl: String): Either<Error, Empty>
    suspend fun getNextUrl(): Either<Error, String?>
}