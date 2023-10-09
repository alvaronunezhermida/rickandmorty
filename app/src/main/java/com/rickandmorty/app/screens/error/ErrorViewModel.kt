package com.rickandmorty.app.screens.error

import com.rickandmorty.app.navigation.ActivityNavigator
import com.rickandmorty.app.navigation.AppNavigator
import com.rickandmorty.app.screens.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ErrorViewModel @Inject constructor(private val appNavigator: AppNavigator) :
    BaseViewModel() {

    fun onOkButtonClicked() {
        appNavigator.goBack()
    }

}