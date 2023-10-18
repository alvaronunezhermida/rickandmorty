package com.rickandmorty.data.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.rickandmorty.data.source.LocalDataSource
import com.rickandmorty.data.source.PreferencesDataSource
import com.rickandmorty.data.source.RemoteDataSource
import com.rickandmorty.domain.Character
import com.rickandmorty.domain.Empty
import com.rickandmorty.domain.Error
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Repository class responsible for managing operations related to characters.
 *
 * This class serves as an intermediary between data sources (remote and local) and provides methods to access and manipulate character data.
 *
 * @property remoteDataSource The remote data source for character information.
 * @property localDataSource The local data source for character information.
 */
class Repository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : BaseRepository() {

    /**
     * A [Flow] representing the list of characters retrieved from the local data source.
     *
     * This property allows you to observe changes to the list of characters in a reactive manner.
     *
     * @see LocalDataSource.characters
     */
    val characters: Flow<List<Character>> get() = localDataSource.characters

    /**
     * Loads all characters from the remote data source and saves them to the local data source.
     *
     * If the local data source is empty, this method fetches all characters from the remote source and stores them locally.
     * If an error occurs during the process, it emits an [Error.Unknown] result.
     *
     * @return A [Flow] of [Either] indicating the operation result:
     *   - If the operation is successful, [Empty] is emitted as [Either.Right].
     *   - If an error occurs, [Error.Unknown] is emitted as [Either.Left].
     */
    fun loadCharacters(): Flow<Either<Error, String?>> = doRun {
        flow {
            val characters = remoteDataSource.getCharacters()
            characters.fold(
                ifLeft = { emit(Error().left()) },
                ifRight = {
                    localDataSource.saveCharacters(it.characters)
                    emit(it.nextUrl.right())
                }
            )
        }
    }

    fun loadMoreCharacters(nextUrl: String): Flow<Either<Error, String?>> = doRun {
        flow {
            val characters = remoteDataSource.getMoreCharacters(nextUrl)
            characters.fold(
                ifLeft = { emit(Error().left()) },
                ifRight = {
                    localDataSource.saveCharacters(it.characters)
                    emit(it.nextUrl.right())
                }
            )
        }
    }

    fun getCharacter(characterId: Int): Flow<Either<Error, Character>> = doRun {
        flow {
            emit(localDataSource.getCharacter(characterId))
        }
    }

    fun saveNextUrl(nextUrl: String): Flow<Either<Error, Empty>> = doRun {
        flow {
            emit(preferencesDataSource.saveNextUrl(nextUrl))
        }
    }

    fun getNextUrl(): Flow<Either<Error, String?>> = doRun {
        flow {
            emit(preferencesDataSource.getNextUrl())
        }
    }

}