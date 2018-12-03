package org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.TranslationId

class SumUpLessonUseCase(val repository: FlashcardsRepository) {

    fun sumUp(lesson: String, translationId: TranslationId, flashcards: List<Flashcard>) {
        repository.updateProgress(lesson, translationId, flashcards)
    }
}