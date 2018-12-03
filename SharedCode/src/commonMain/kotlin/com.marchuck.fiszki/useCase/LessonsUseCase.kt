package org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.TranslationId

class LessonsUseCase(val flashcardsRepository: FlashcardsRepository) {

    fun getLessons() = flashcardsRepository.getFlashCardLessons()

    fun getLesson(lesson: String,translationId: TranslationId) = flashcardsRepository.getLesson(lesson,translationId)
}