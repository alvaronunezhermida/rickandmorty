package com.rickandmorty.usecases

import com.rickandmorty.data.repository.Repository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke() = repository.characters
}