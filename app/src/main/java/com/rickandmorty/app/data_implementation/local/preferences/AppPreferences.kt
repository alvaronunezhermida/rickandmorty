package com.rickandmorty.app.data_implementation.local.preferences

import android.content.SharedPreferences
import arrow.core.Either
import arrow.core.right
import com.rickandmorty.domain.Empty
import com.rickandmorty.domain.Error
import timber.log.Timber
import javax.inject.Inject

class AppPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveNextUrl(nextUrl: String): Either<Error, Empty> = doRun {
        sharedPreferences.edit().apply {
            putString(AppPreferencesConfig.NEXT_URL_KEY, nextUrl)
        }.apply()
        Empty().right()
    }

    fun getNextUrl(): Either<Error, String?> = doRun {
    sharedPreferences.getString(AppPreferencesConfig.NEXT_URL_KEY, null).right()
    }

    private fun <T> doRun(call: () -> Either<Error, T>): Either<Error, T> {
        return try {
            call()
        } catch (exception: Exception) {
            Timber.e(exception)
            Either.Left(Error())
        }
    }

}