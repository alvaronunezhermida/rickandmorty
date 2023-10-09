package com.rickandmorty.app.screens.breeds

import com.rickandmorty.app.navigation.AppNavigator
import com.rickandmorty.app.screens.AppViewModel
import com.rickandmorty.domain.Character
import com.rickandmorty.usecases.GetCharactersUseCase
import com.rickandmorty.usecases.LoadCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class BreedsViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val loadCharactersUseCase: LoadCharactersUseCase,
    appNavigator: AppNavigator
) : AppViewModel(appNavigator = appNavigator) {

    private val breedsMutableState = MutableStateFlow(emptyList<Character>())
    val breedsState: StateFlow<List<Character>>
        get() = breedsMutableState

    override fun onStarted() {
        super.onStarted()
        launchGetBreeds()
        launchLoadAllBreeds()
    }

    private fun launchGetBreeds() {
        launch {
            getCharactersUseCase().collect { breeds ->
                breedsMutableState.value = breeds
            }
        }
    }

    private fun launchLoadAllBreeds() {
        launch {
            loadCharactersUseCase().collect { either ->
                either.fold(
                    ifLeft = { error ->
                        appNavigator.toError(error)
                    },
                    ifRight = { _ ->
                        // Do nothing
                    }
                )

            }
        }
    }


    fun onBreedClicked(character: Character) {
        appNavigator.fromBreedsToBreedImages(character.breedName)
    }

}