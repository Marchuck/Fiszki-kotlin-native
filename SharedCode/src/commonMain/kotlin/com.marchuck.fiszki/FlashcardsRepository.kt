package org.kotlin.mpp.mobile.com.marchuck.fiszki

import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.KnowledgeProgress

interface FlashcardsRepository {

    fun createLesson(lesson: String, flashcards: List<Flashcard>)

    fun getLesson(lesson: String): List<Flashcard>

    fun resetProgress(lesson: String)

    fun updateProgress(lesson: String, knowledgeProgress: KnowledgeProgress)

    fun getFlashCardLessons(): List<String>
}