package com.rickandmorty.app.screens.characters

import app.cash.turbine.test
import arrow.core.right
import com.rickandmorty.app.testcommons.CoroutinesTestRule
import com.rickandmorty.app.testcommons.sampleCharacter
import com.rickandmorty.domain.Empty
import com.rickandmorty.usecases.GetCharactersUseCase
import com.rickandmorty.usecases.GetNextUrlUseCase
import com.rickandmorty.usecases.LoadCharactersUseCase
import com.rickandmorty.usecases.LoadMoreCharactersUseCase
import com.rickandmorty.usecases.SaveNextUrlUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

    @Mock
    lateinit var loadCharactersUseCase: LoadCharactersUseCase

    @Mock
    lateinit var loadMoreCharactersUseCase: LoadMoreCharactersUseCase

    @Mock
    lateinit var saveNextUrlUseCase: SaveNextUrlUseCase

    @Mock
    lateinit var getNextUrlUseCase: GetNextUrlUseCase

    private lateinit var vm: CharactersViewModel

    private val characters = listOf(sampleCharacter)

    @Before
    fun setUp() {
        whenever(getCharactersUseCase()).thenReturn(flowOf(characters))
        whenever(loadCharactersUseCase()).thenReturn(flowOf("nextUrl".right()))
        whenever(loadMoreCharactersUseCase()).thenReturn(flowOf("nextUrl".right()))
        whenever(getNextUrlUseCase()).thenReturn(flowOf("nextUrl".right()))
        whenever(saveNextUrlUseCase()).thenReturn(flowOf(Empty().right()))

        vm = CharactersViewModel(
            getCharactersUseCase,
            loadCharactersUseCase,
            loadMoreCharactersUseCase,
            saveNextUrlUseCase,
            getNextUrlUseCase
        )
    }

    @Test
    fun `Characters are loaded from cache when viewmodel starts`() = runTest {
        runCurrent()
        verify(getCharactersUseCase).invoke()
    }

    @Test
    fun `State is updated with current cached content immediately`() = runTest {
        vm.charactersState.test {
            assertEquals(characters, awaitItem())
        }
    }

    @Test
    fun `Characters are requested when viewmodel starts and nextUrl saved`() = runTest {
        runCurrent()
        verify(loadCharactersUseCase).invoke()
        verify(saveNextUrlUseCase).invoke(any())
    }
}
