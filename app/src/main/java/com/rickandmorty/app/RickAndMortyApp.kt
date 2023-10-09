package com.rickandmorty.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class RickAndMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        //TODO: Create BuildConfig for debug and release and initialize Timber only in debug
        Timber.plant(Timber.DebugTree())
    }
}