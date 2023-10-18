package com.rickandmorty.usecases

import arrow.core.Either
import arrow.core.left
import com.rickandmorty.data.repository.Repository
import com.rickandmorty.domain.Error
import com.rickandmorty.usecases.LoadMoreCharactersUseCase.Params
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadMoreCharactersUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<Params, String?>() {

    override fun run(params: Params?): Flow<Either<Error, String?>> = params?.run {
        repository.loadMoreCharacters(nextUrl = params.nextUrl)
    } ?: flow {
        emit(Error.NullParams.left())
    }


    data class Params(
        val nextUrl: String,
    )
}