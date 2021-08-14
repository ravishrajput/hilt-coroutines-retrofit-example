package com.ravish.hilt.coroutines.retrofit.example

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltCoroutinesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}