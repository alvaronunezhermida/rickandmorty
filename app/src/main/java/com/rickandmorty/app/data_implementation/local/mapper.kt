package com.rickandmorty.app.data_implementation.local

import com.rickandmorty.app.data_implementation.local.entities.CharacterEntity
import com.rickandmorty.domain.Character

fun List<CharacterEntity>.toDomainBreedList() = map { Character(it.breedName) }

fun List<Character>.toBreedEntityList() = map { CharacterEntity(null, it.breedName) }