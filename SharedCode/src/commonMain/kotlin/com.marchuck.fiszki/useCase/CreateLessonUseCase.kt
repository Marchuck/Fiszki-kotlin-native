package org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson

class CreateLessonUseCase(val flashcardsRepository: FlashcardsRepository) {

    fun createLesson(lesson: Lesson,
                     flashcards: List<Flashcard>) = flashcardsRepository.createLesson(lesson, flashcards)

    fun getLesson(lesson: Long) = flashcardsRepository.getLesson(lesson)

    fun getFlashcardsRange() = Pair(3, 30)

}