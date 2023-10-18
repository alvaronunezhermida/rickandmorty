package com.rickandmorty.app.data_implementation.local.room

import com.rickandmorty.app.data_implementation.local.room.entities.CharacterEntity
import com.rickandmorty.app.data_implementation.local.room.entities.LocationEntity
import com.rickandmorty.app.data_implementation.local.room.entities.OriginEntity
import com.rickandmorty.domain.Character
import com.rickandmorty.domain.Location
import com.rickandmorty.domain.Origin

fun CharacterEntity.toDomainCharacter() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.toDomainOrigin(),
    location = location.toDomainLocation(),
    image = image,
    episode = episode,
    url = url,
    created = created
)

fun Character.toEntity() = CharacterEntity(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    origin = origin.toEntity(),
    location = location.toEntity(),
    image = image,
    episode = episode,
    url = url,
    created = created
)

fun OriginEntity.toDomainOrigin() = Origin(
    name = name,
    url = url
)

fun Origin.toEntity() = OriginEntity(
    name = name,
    url = url
)

fun LocationEntity.toDomainLocation() = Location(
    name = name,
    url = url
)

fun Location.toEntity() = LocationEntity(
    name = name,
    url = url
)
fun List<CharacterEntity>.toDomainCharacterList() = map { it.toDomainCharacter() }

fun List<Character>.toCharacterEntityList() = map { it.toEntity() }