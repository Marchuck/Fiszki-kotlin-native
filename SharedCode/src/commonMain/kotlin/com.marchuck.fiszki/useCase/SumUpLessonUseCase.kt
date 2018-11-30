package org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.KnowledgeProgress

class SumUpLessonUseCase(val repository: FlashcardsRepository) {

    fun sumUp(lesson: String, progress: KnowledgeProgress) {
        repository.updateProgress(lesson,progress)
    }
}