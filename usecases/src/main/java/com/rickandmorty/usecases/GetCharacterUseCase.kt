package com.rickandmorty.usecases

import arrow.core.Either
import arrow.core.left
import com.rickandmorty.data.repository.Repository
import com.rickandmorty.domain.Character
import com.rickandmorty.domain.Error
import com.rickandmorty.usecases.GetCharacterUseCase.Params
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(private val repository: Repository) :
    BaseUseCase<Params, Character>() {

    override fun run(params: Params?): Flow<Either<Error, Character>> = params?.run {
        repository.getCharacter(characterId = params.characterId)
    } ?: flow {
        emit(Error().left())
    }


    data class Params(
        val characterId: Int,
    )
}