package org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository

class LessonsUseCase(val flashcardsRepository: FlashcardsRepository) {

    fun getLessons() = flashcardsRepository.getFlashCardLessons()

    fun getLesson(lesson: String) = flashcardsRepository.getLesson(lesson)
}