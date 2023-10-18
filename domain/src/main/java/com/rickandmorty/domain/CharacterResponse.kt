package com.rickandmorty.domain

data class CharacterResponse(
    val nextUrl: String?,
    val characters: List<Character>
)