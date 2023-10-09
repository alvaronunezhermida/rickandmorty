package com.rickandmorty.usecases

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetCharactersUseCaseTest {

    @Test
    fun `Invoke calls characters repository`(): Unit = runBlocking {
        val sampleCharacters = flowOf(listOf(sampleCharacter))
        val getCharactersUseCase = GetCharactersUseCase(mock {
            on { characters } doReturn sampleCharacters
        })

        val result = getCharactersUseCase()

        Assert.assertEquals(sampleCharacters, result)
    }

}