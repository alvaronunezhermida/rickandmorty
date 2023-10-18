package com.rickandmorty.app.data_implementation.remote.mappers

import com.rickandmorty.app.data_implementation.remote.models.CharacterDTO
import com.rickandmorty.app.data_implementation.remote.models.CharactersResponseDTO
import com.rickandmorty.app.data_implementation.remote.models.LocationDTO
import com.rickandmorty.app.data_implementation.remote.models.OriginDTO
import com.rickandmorty.domain.Character
import com.rickandmorty.domain.CharacterResponse
import com.rickandmorty.domain.Location
import com.rickandmorty.domain.Origin

fun CharactersResponseDTO.toDomain(): CharacterResponse =
    CharacterResponse(nextUrl = info?.next,
        characters = characters?.map { it.toDomain() } ?: emptyList())

fun CharacterDTO.toDomain(): Character = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.toDomain(),
    location = location.toDomain(),
    image = image,
    episode = episode,
    url = url,
    created = created
)

fun OriginDTO.toDomain(): Origin = Origin(
    name = name,
    url = url
)

fun LocationDTO.toDomain(): Location = Location(
    name = name,
    url = url
)