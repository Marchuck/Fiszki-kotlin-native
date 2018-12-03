package org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons

sealed class LessonsViewState {

    object Loading : LessonsViewState()

    data class Content(val lessons: List<String>) : LessonsViewState()

    data class Error(val message: String) : LessonsViewState()

    data class EnterLesson(val lesson: String) : LessonsViewState()
}