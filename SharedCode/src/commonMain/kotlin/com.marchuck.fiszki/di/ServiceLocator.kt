package org.kotlin.mpp.mobile.com.marchuck.fiszki.di

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.KnowledgeProgress
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.LessonsPresenter
import org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase.LessonsUseCase

object ServiceLocator {

    //todo: override on initialization
    var flashcardsRepository: FlashcardsRepository = object : FlashcardsRepository {
        override fun createLesson(lesson: String, flashcards: List<Flashcard>) {}

        override fun getLesson(lesson: String) = emptyList<Flashcard>()

        override fun resetProgress(lesson: String) {}

        override fun updateProgress(lesson: String, knowledgeProgress: KnowledgeProgress) {}

        override fun getFlashCardLessons() = emptyList<String>()
    }

    val lessonUseCase: LessonsUseCase by lazy {
        LessonsUseCase(flashcardsRepository)
    }

    fun provideLessonsPresenter(): LessonsPresenter {
        return LessonsPresenter(lessonUseCase)
    }
}
