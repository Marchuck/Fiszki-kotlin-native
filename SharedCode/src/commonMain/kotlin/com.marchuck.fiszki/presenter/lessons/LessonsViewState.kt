package org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons

import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson

sealed class LessonsViewState {

    object Loading : LessonsViewState()

    data class Content(val lessons: List<Lesson>) : LessonsViewState()

    data class Error(val message: String) : LessonsViewState()

    data class EnterLesson(val lesson: Lesson) : LessonsViewState()
}