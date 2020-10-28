package org.kotlin.mpp.mobile.com.marchuck.fiszki

import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson

interface FlashcardsRepository {

    fun createLesson(lesson: Lesson, flashcards: List<Flashcard>)

    fun removeLesson(lesson_id: Long)

    fun getLesson(lesson_id: Long): Lesson

    fun getLessons(): List<Lesson>

    fun getFlashcards(lesson_id: Long): List<Flashcard>

    fun updateFlashcards(lesson_id: Long, flashcards: List<Flashcard>)
}