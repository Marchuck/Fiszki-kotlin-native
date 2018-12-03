package org.kotlin.mpp.mobile.com.marchuck.fiszki.di

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.KnowledgeProgress
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.TranslationId
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons.LessonsPresenter
import org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase.LessonsUseCase

object ServiceLocator {

    //todo: override on initialization
    var flashcardsRepository: FlashcardsRepository = object : FlashcardsRepository {
        override fun getLesson(lesson: String, translationId: TranslationId): List<Flashcard> {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun resetProgress(lesson: String, translationId: TranslationId) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun updateProgress(lesson: String, translationId: TranslationId, flashcards: List<Flashcard>) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun createLesson(lesson: String, translationId: TranslationId, flashcards: List<Flashcard>) {

        }


        override fun getFlashCardLessons() = emptyList<String>()
    }

    val lessonUseCase: LessonsUseCase by lazy {
        LessonsUseCase(flashcardsRepository)
    }

    fun provideLessonsPresenter(): LessonsPresenter {
        return LessonsPresenter(lessonUseCase)
    }

    fun provideLearningPresenter(): LessonsPresenter {
        return LessonsPresenter(lessonUseCase)
    }
}
