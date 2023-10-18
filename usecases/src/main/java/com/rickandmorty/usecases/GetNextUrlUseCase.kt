package com.rickandmorty.usecases

import arrow.core.Either
import com.rickandmorty.data.repository.Repository
import com.rickandmorty.domain.Error
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNextUrlUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<Void, String?>() {

    override fun run(params: Void?): Flow<Either<Error, String?>> =
        repository.getNextUrl()
}