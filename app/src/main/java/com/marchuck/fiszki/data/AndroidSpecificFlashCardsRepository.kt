package com.marchuck.fiszki.data

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.TranslationId
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.resetProgress


class FlashcardList(c: List<Flashcard>) : ArrayList<Flashcard>(c)
class StringArrayList : ArrayList<String>()

class AndroidSpecificFlashCardsRepository(val preferences: SharedPreferences,
                                          val gson: Gson) : FlashcardsRepository {

    fun lessonKeyOf(lesson: String, translationId: TranslationId): String {
        return LESSON_KEY + lesson + "_" + translationId.name
    }

    override fun createLesson(lesson: String,
                              translationId: TranslationId,
                              flashcards: List<Flashcard>) {
        preferences.edit {
            it.putString(lessonKeyOf(lesson, translationId), gson.toJson(FlashcardList(flashcards)))
        }
    }

    companion object {
        val LESSON_KEY = "LESSON_KEY"
        val LESSONS = "LESSONS"
    }

    override fun getLesson(lesson: String, translationId: TranslationId): List<Flashcard> {
        val json = preferences.getString(lessonKeyOf(lesson, translationId), "")
        if (json?.isNotEmpty() == true) {
            try {
                return gson.fromJson(json, FlashcardList::class.java)
            } catch (ex: JsonSyntaxException) {
            }
        }
        return emptyList()
    }

    override fun resetProgress(lesson: String, translationId: TranslationId) {
        val flashCards = getLesson(lesson, translationId)
        val newOne = arrayListOf<Flashcard>()
        for (j in flashCards) {
            newOne.add(j.resetProgress())
        }
        createLesson(lesson, translationId, newOne)
    }

    override fun updateProgress(lesson: String, translationId: TranslationId, flashcards: List<Flashcard>) {
        createLesson(lesson, translationId, flashcards)
    }

    override fun getFlashCardLessons(): List<String> {

        for (key in preferences.all){
            if (key.key.startsWith(LESSON_KEY)){

            }
        }


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