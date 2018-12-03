package org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons

import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.base.BasePresenter
import org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase.LessonsUseCase

class LessonsPresenter(val lessonsUseCase: LessonsUseCase) : BasePresenter<LessonsView>() {

    fun requestLessons() {
        view?.render(LessonsViewState.Loading)
        val lessons = lessonsUseCase.getLessons()

        view?.render(LessonsViewState.Content(lessons))
    }

    fun onLessonClick(lesson: Lesson){
        view?.render(LessonsViewState.EnterLesson(lesson))
    }

}