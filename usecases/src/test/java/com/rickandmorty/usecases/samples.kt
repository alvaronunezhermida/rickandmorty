package com.rickandmorty.usecases

import com.rickandmorty.domain.Character
import com.rickandmorty.domain.Location
import com.rickandmorty.domain.Origin

internal val sampleCharacter = Character(
    361,
    "Toxic Rick",
    "Dead",
    "Humanoid",
    "Rick's Toxic Side",
    "Male",
    Origin("Alien Spa", "https://rickandmortyapi.com/api/location/64"),
    Location("Earth", "https://rickandmortyapi.com/api/location/20"),
    "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
    listOf("https://rickandmortyapi.com/api/episode/27"),
    "https://rickandmortyapi.com/api/character/361",
    "2018-01-10T18:20:41.703Z"
)