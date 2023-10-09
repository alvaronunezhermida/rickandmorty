package com.rickandmorty.app.screens

import com.rickandmorty.app.navigation.AppNavigator
import com.rickandmorty.domain.Error

abstract class AppViewModel(
    protected val appNavigator: AppNavigator,
) : BaseViewModel() {

    override fun handleError(error: Error) {
        appNavigator.toError(error = error)
    }

}