package com.marchuck.fiszki

import android.app.Application
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.marchuck.fiszki.data.AndroidSpecificFlashCardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.di.ServiceLocator

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ServiceLocator.flashcardsRepository = AndroidSpecificFlashCardsRepository(
                PreferenceManager.getDefaultSharedPreferences(this),
                Gson()
        )
    }
}