package org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.TranslationId

class CreateLessonUseCase(val flashcardsRepository: FlashcardsRepository) {

    fun createLesson(lesson: String,
                     translationId: TranslationId,
                     flashcards: List<Flashcard>) = flashcardsRepository.createLesson(lesson, translationId, flashcards)

    fun getLesson(lesson: String, translationId: TranslationId) = flashcardsRepository.getLesson(lesson, translationId)

    fun getFlashcardsRange() = Pair(3, 30)

}