package com.rickandmorty.app.screens.character_detail

import com.rickandmorty.app.navigation.AppNavigator
import com.rickandmorty.app.screens.AppViewModel
import com.rickandmorty.domain.Character
import com.rickandmorty.usecases.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    appNavigator: AppNavigator
) : AppViewModel(appNavigator = appNavigator) {

    private val characterMutableState = MutableStateFlow(null)
    val characterState: StateFlow<Character?>
        get() = characterMutableState

    init {
        launchGetCharacter()
    }

    private fun launchGetCharacter() {
        launch {
            getCharacterUseCase().collect { either ->
                either.fold(
                    { error ->
                        appNavigator.toError(error)
                    },
                    { character ->
                        characterMutableState.value = character
                    }
                )
            }
        }
    }

}