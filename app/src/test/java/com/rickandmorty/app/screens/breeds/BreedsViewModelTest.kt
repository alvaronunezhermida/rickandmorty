package com.rickandmorty.app.screens.breeds

import app.cash.turbine.test
import arrow.core.right
import com.rickandmorty.app.navigation.AppNavigator
import com.rickandmorty.app.testcommons.CoroutinesTestRule
import com.rickandmorty.app.testcommons.sampleCharacter
import com.rickandmorty.domain.Empty
import com.rickandmorty.usecases.GetCharactersUseCase
import com.rickandmorty.usecases.LoadCharactersUseCase
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BreedsViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

    @Mock
    lateinit var loadCharactersUseCase: LoadCharactersUseCase

    @Mock
    lateinit var appNavigator: AppNavigator

    private lateinit var vm: BreedsViewModel

    private val breeds = listOf(sampleCharacter)

    @Before
    fun setUp() {
        whenever(getCharactersUseCase()).thenReturn(flowOf(breeds))
        whenever(loadCharactersUseCase()).thenReturn(flowOf(Empty().right()))
        vm = BreedsViewModel(getCharactersUseCase, loadCharactersUseCase, appNavigator)
        vm.onStarted()
    }

    @Test
    fun `Breeds are loaded from cache when viewmodel starts`() = runTest {
        runCurrent()
        verify(getCharactersUseCase).invoke()
    }

    @Test
    fun `State is updated with current cached content immediately`() = runTest {
        vm.breedsState.test {
            assertEquals(breeds, awaitItem())
        }
    }

    @Test
    fun `Breeds are requested when viewmodel starts`() = runTest {
        runCurrent()
        verify(loadCharactersUseCase).invoke()
    }
}
