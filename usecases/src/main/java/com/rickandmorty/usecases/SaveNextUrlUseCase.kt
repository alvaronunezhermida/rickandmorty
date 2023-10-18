package com.rickandmorty.usecases

import arrow.core.Either
import arrow.core.left
import com.rickandmorty.data.repository.Repository
import com.rickandmorty.domain.Empty
import com.rickandmorty.domain.Error
import com.rickandmorty.usecases.SaveNextUrlUseCase.Params
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveNextUrlUseCase @Inject constructor(private val repository: Repository
) : BaseUseCase<Params, Empty>() {

    override fun run(params: Params?): Flow<Either<Error, Empty>> = params?.run {
        repository.saveNextUrl(nextUrl = nextUrl)
    } ?: flow {
        emit(Error.Unknown.left())
    }

    class Params(val nextUrl: String)
}