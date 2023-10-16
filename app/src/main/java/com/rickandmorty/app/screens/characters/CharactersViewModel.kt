package com.rickandmorty.app.screens.characters

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
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val loadCharactersUseCase: LoadCharactersUseCase,
    appNavigator: AppNavigator
) : AppViewModel(appNavigator = appNavigator) {

    private val charactersMutableState = MutableStateFlow(emptyList<Character>())
    val charactersState: StateFlow<List<Character>>
        get() = charactersMutableState

    init {
        launchGetCharacters()
        launchLoadAllCharacters()
    }

    private fun launchGetCharacters() {
        launch {
            getCharactersUseCase().collect { characters ->
                charactersMutableState.value = characters
            }
        }
    }

    private fun launchLoadAllCharacters() {
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


    fun onCharacterClicked(character: Character) {
        //TODO: not implemented
    }

}