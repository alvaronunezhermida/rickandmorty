package com.rickandmorty.app.screens.error

import com.rickandmorty.domain.Error

interface ErrorListener {
    fun onErrorOkClicked(error: Error)
}