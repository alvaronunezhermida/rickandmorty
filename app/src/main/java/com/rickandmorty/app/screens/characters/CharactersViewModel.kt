package com.rickandmorty.app.screens.characters

import com.rickandmorty.app.navigation.AppNavigator
import com.rickandmorty.app.screens.AppViewModel
import com.rickandmorty.domain.Character
import com.rickandmorty.usecases.GetCharactersUseCase
import com.rickandmorty.usecases.GetNextUrlUseCase
import com.rickandmorty.usecases.LoadCharactersUseCase
import com.rickandmorty.usecases.LoadMoreCharactersUseCase
import com.rickandmorty.usecases.SaveNextUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val loadCharactersUseCase: LoadCharactersUseCase,
    private val loadMoreCharactersUseCase: LoadMoreCharactersUseCase,
    private val saveNextUrlUseCase: SaveNextUrlUseCase,
    private val getNextUrl: GetNextUrlUseCase,
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
                    ifRight = {
                        it?.let { nextUrl ->
                            saveNextUrlUseCase(SaveNextUrlUseCase.Params(nextUrl))
                        }
                    }
                )

            }
        }
    }

    private fun launchLoadMoreCharacters() {
        launch {
            getNextUrl().collect { either ->
                either.fold(
                    ifLeft = { error ->
                        appNavigator.toError(error)
                    },
                    ifRight = {nextUrl ->
                        nextUrl?.let {
                            loadMoreCharactersUseCase(LoadMoreCharactersUseCase.Params(nextUrl)).collect { either ->
                                either.fold(
                                    ifLeft = { error ->
                                        appNavigator.toError(error)
                                    },
                                    ifRight = {
                                        it?.let { nextUrl ->
                                            saveNextUrlUseCase(SaveNextUrlUseCase.Params(nextUrl))
                                        }
                                    }
                                )

                            }
                        }
                    }
                )

            }

        }
    }


    fun loadMore() {
        launchLoadMoreCharacters()
    }

}