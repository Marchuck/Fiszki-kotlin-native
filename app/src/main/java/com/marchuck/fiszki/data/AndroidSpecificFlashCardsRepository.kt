package com.marchuck.fiszki.data

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.resetProgress


class FlashcardList(c: List<Flashcard>) : ArrayList<Flashcard>(c)
class StringArrayList : ArrayList<String>()

class AndroidSpecificFlashCardsRepository(val preferences: SharedPreferences,
                                          val gson: Gson) : FlashcardsRepository {

    companion object {
        val LESSON_KEY = "LESSON_KEY"
        val LESSONS = "LESSONS"
    }

    override fun createLesson(lesson: String, flashcards: List<Flashcard>) {
        preferences.edit {

            it.putString(LESSON_KEY + lesson, gson.toJson(FlashcardList(flashcards)))
        }
    }

    override fun getLesson(lesson: String): List<Flashcard> {
        val json = preferences.getString(LESSON_KEY + lesson, "")
        if (json?.isNotEmpty() == true) {
            try {
                return gson.fromJson(json, FlashcardList::class.java)
            } catch (ex: JsonSyntaxException) {
            }
        }
        return emptyList()
    }

    override fun resetProgress(lesson: String) {
        val flashCards = getLesson(lesson)
        val newOne = arrayListOf<Flashcard>()
        for (j in flashCards) {
            newOne.add(j.resetProgress())
        }
        createLesson(lesson, newOne)
    }

    override fun updateProgress(lesson: String, flashcards: List<Flashcard>) {
        createLesson(lesson, flashcards)
    }

    override fun getFlashCardLessons(): List<String> {
        val json = preferences.getString(LESSONS, "")
        if (json?.isNotEmpty() == true) {
            try {
                return gson.fromJson(json, StringArrayList::class.java)
            } catch (ex: JsonSyntaxException) {
            }
        }
        return emptyList()
    }

    @SuppressLint("ApplySharedPref")
    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.commit()
    }
}