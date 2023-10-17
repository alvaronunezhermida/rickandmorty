package com.rickandmorty.app.screens.character_detail

import androidx.lifecycle.SavedStateHandle
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
    savedStateHandle: SavedStateHandle,
    appNavigator: AppNavigator
) : AppViewModel(appNavigator = appNavigator) {

    //private val characterId = savedStateHandle.get<Int>(ItemId)

    private val characterMutableState = MutableStateFlow<Character?>(null)
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