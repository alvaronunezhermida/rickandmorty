package com.rickandmorty.usecases

import com.rickandmorty.data.repository.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class LoadCharactersUseCaseTest {

    @Test
    fun `Invoke calls characters repository`(): Unit = runBlocking {
        val repository = mock<Repository>()
        val loadCharactersUseCase = LoadCharactersUseCase(repository)

        loadCharactersUseCase()

        verify(repository).loadCharacters()
    }
}