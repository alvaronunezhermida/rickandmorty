package com.rickandmorty.app.data_implementation.remote

import com.rickandmorty.app.data_implementation.remote.models.CharactersResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("character")
    suspend fun getCharacters(): Response<CharactersResponseDTO>

}