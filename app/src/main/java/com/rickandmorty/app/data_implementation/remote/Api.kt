package com.rickandmorty.app.data_implementation.remote

import com.rickandmorty.app.data_implementation.remote.models.CharactersResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {

    @GET("character")
    suspend fun getCharacters(): Response<CharactersResponseDTO>

    @GET
    suspend fun getMoreCharacters(
        @Url nextUrl: String
    ): Response<CharactersResponseDTO>

}