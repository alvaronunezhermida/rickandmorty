package com.rickandmorty.app.data_implementation.remote.clients

import arrow.core.Either
import com.rickandmorty.data.source.RemoteDataSource
import com.rickandmorty.domain.*
import com.rickandmorty.app.data_implementation.remote.BaseRemote
import com.rickandmorty.app.data_implementation.remote.Api
import com.rickandmorty.app.data_implementation.remote.mappers.toDomain
import javax.inject.Inject

class Client @Inject constructor(
    private val api: Api,
) : RemoteDataSource, BaseRemote() {

    override suspend fun getCharacters(): Either<Error, CharacterResponse> = doRun(
        getResponse = {
            api.getCharacters()
        },
        map = { dto ->
            dto.toDomain()
        }
    )

    override suspend fun getMoreCharacters(nextUrl: String): Either<Error, CharacterResponse> = doRun(
        getResponse = {
            api.getMoreCharacters(nextUrl)
        },
        map = { dto ->
            dto.toDomain()
        }
    )
}