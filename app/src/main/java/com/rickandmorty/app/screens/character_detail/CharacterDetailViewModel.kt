package com.rickandmorty.app.screens.character_detail

import androidx.lifecycle.SavedStateHandle
import com.rickandmorty.app.screens.BaseViewModel
import com.rickandmorty.domain.Character
import com.rickandmorty.usecases.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val characterId = savedStateHandle.get<Int>("characterId")

    private val characterMutableState = MutableStateFlow<Character?>(null)
    val characterState: StateFlow<Character?>
        get() = characterMutableState

    init {
        launchGetCharacter()
    }

    private fun launchGetCharacter() {
        characterId?.let {
            launch {
                getCharacterUseCase(GetCharacterUseCase.Params(it)).collect { either ->
                    either.fold(
                        { error ->
                            //TODO: handle error
                        },
                        { character ->
                            characterMutableState.value = character
                        }
                    )
                }
            }
        } ?: {
            //TODO: handle error
        }
    }

}