package com.marchuck.fiszki

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.marchuck.fiszki.data.AndroidSpecificFlashCardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.di.ServiceLocator

class App : Application() {

    companion object {
        var _context: Context? = null
        val repository: FlashcardsRepository by lazy {
            AndroidSpecificFlashCardsRepository(
                    PreferenceManager.getDefaultSharedPreferences(_context),
                    Gson()
            )
        }

        fun getFlashCardsRepository(): FlashcardsRepository {
            return repository
        }
    }

    override fun onCreate() {
        super.onCreate()

        _context = this
        ServiceLocator.flashcardsRepository = getFlashCardsRepository()
    }

}