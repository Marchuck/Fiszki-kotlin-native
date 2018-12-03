package org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository

class LessonsUseCase(val flashcardsRepository: FlashcardsRepository) {

    fun getLessons() = flashcardsRepository.getLessons()

    fun getLesson(lesson_id: Long) = flashcardsRepository.getLesson(lesson_id)
}