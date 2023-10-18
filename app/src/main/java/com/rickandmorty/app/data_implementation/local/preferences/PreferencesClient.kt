package com.rickandmorty.app.data_implementation.local.preferences

import arrow.core.Either
import com.rickandmorty.data.source.PreferencesDataSource
import com.rickandmorty.domain.Empty
import com.rickandmorty.domain.Error
import javax.inject.Inject

class PreferencesClient @Inject constructor(private val appPreferences: AppPreferences) :
    PreferencesDataSource {

    override suspend fun saveNextUrl(nextUrl: String): Either<Error, Empty> =
        appPreferences.saveNextUrl(nextUrl)

    override suspend fun getNextUrl(): Either<Error, String?> =
        appPreferences.getNextUrl()

}