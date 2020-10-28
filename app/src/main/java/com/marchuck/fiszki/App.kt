package com.marchuck.fiszki

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.marchuck.fiszki.data.AndroidSpecificFlashCardsRepository
import com.marchuck.fiszki.data.db.FlashcardLessonsDatabase
import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.di.ServiceLocator

class App : Application() {


    companion object {
        var _context: Context? = null
        val repository: FlashcardsRepository by lazy {
            AndroidSpecificFlashCardsRepository(
                    roomDatabase.getLessonsDao(),
                    roomDatabase.getFlashcardsDao()
            )
        }

        val roomDatabase: FlashcardLessonsDatabase  by lazy {
            FlashcardLessonsDatabase.getInstance(_context!!)
        }

        fun getFlashCardsRepository(): FlashcardsRepository {
            return repository
        }

        fun showToast(message: String) {
            Toast.makeText(_context, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate() {
        super.onCreate()

        _context = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        ServiceLocator.flashcardsRepository = getFlashCardsRepository()
    }

}