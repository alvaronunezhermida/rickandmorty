package com.rickandmorty.data

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.rickandmorty.data.repository.Repository
import com.rickandmorty.data.source.LocalDataSource
import com.rickandmorty.data.source.RemoteDataSource
import com.rickandmorty.domain.Character
import com.rickandmorty.domain.Error
import com.rickandmorty.domain.Location
import com.rickandmorty.domain.Origin
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    private lateinit var repository: Repository

    private val localCharacters =
        flowOf(listOf(sampleCharacter, sampleCharacter2))

    @Before
    fun setUp() {
        whenever(localDataSource.characters).thenReturn(localCharacters)
        repository = Repository(remoteDataSource, localDataSource)
    }

    @Test
    fun `Characters are taken from local data source if available`(): Unit = runTest {
        val result = repository.characters

        assertEquals(localCharacters, result)
    }

    @Test
    fun `Characters are saved to local data source when it's empty`(): Unit = runTest {
        val remoteCharacters = listOf(sampleCharacter)
        whenever(localDataSource.isCharactersListEmpty()).thenReturn(true)
        whenever(remoteDataSource.getCharacters()).thenReturn(remoteCharacters.right())

        repository.loadCharacters().collect {
            it.fold(
                { error -> assertEquals(Error.Unknown, error) },
                { verify(localDataSource).saveCharacters(remoteCharacters) }
            )
        }


    }
}

private val sampleCharacter = Character(
    361,
    "Toxic Rick",
    "Dead",
    "Humanoid",
    "Rick's Toxic Side",
    "Male",
    Origin("Alien Spa", "https://rickandmortyapi.com/api/location/64"),
    Location("Earth", "https://rickandmortyapi.com/api/location/20"),
    "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
    listOf("https://rickandmortyapi.com/api/episode/27"),
    "https://rickandmortyapi.com/api/character/361",
    "2018-01-10T18:20:41.703Z"
)

private val sampleCharacter2 = Character(
    1,
    "Rick Sanchez",
    "Alive",
    "Human",
    "",
    "Male",
    Origin("Earth (C-137)", "https://rickandmortyapi.com/api/location/1"),
    Location("Citadel of Ricks", "https://rickandmortyapi.com/api/location/3"),
    "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    listOf(
        "https://rickandmortyapi.com/api/episode/1",
        "https://rickandmortyapi.com/api/episode/2"
    ),
    "https://rickandmortyapi.com/api/character/1",
    "2017-11-04T18:48:46.250Z"
)

private fun <T> doRun(block: () -> Flow<Either<Error, T>>): Flow<Either<Error, T>> = block()
    .catch {
        emit(Error.Unknown.left())
    }.flowOn(Dispatchers.IO)