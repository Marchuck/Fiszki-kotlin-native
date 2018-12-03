package org.kotlin.mpp.mobile.com.marchuck.fiszki.di

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons.LessonsPresenter
import org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase.LessonsUseCase

object ServiceLocator {

    var flashcardsRepository: FlashcardsRepository? = null

    val lessonUseCase: LessonsUseCase by lazy {
        LessonsUseCase(flashcardsRepository!!)
    }

    fun provideLessonsPresenter(flashcardsRepository: FlashcardsRepository): LessonsPresenter {
        this.flashcardsRepository = flashcardsRepository
        return LessonsPresenter(lessonUseCase)
    }

    fun provideLearningPresenter(flashcardsRepository: FlashcardsRepository): LessonsPresenter {
        this.flashcardsRepository = flashcardsRepository
        return LessonsPresenter(lessonUseCase)
    }
}
