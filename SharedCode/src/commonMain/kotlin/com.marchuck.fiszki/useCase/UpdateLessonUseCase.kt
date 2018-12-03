package org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard

class UpdateLessonUseCase(val repository: FlashcardsRepository) {

    fun update(lesson_id: Long, flashcards: List<Flashcard>) {
        repository.updateFlashcards(lesson_id, flashcards)
    }
}