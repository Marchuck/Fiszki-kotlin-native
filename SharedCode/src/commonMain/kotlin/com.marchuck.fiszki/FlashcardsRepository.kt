package org.kotlin.mpp.mobile.com.marchuck.fiszki

import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.TranslationId

interface FlashcardsRepository {

    fun createLesson(lesson: String, translationId: TranslationId, flashcards: List<Flashcard>)

    fun getLesson(lesson: String, translationId: TranslationId): List<Flashcard>

    fun resetProgress(lesson: String, translationId: TranslationId)

    fun updateProgress(lesson: String, translationId: TranslationId, flashcards: List<Flashcard>)

    fun getFlashCardLessons(): List<String>
}