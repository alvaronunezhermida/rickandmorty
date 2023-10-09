package com.rickandmorty.usecases

import arrow.core.Either
import com.rickandmorty.data.repository.Repository
import com.rickandmorty.domain.Empty
import com.rickandmorty.domain.Error
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadCharactersUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<Void, Empty>() {

    override fun run(params: Void?): Flow<Either<Error, Empty>> =
        repository.loadCharacters()
}