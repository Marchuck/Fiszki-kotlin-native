package com.marchuck.fiszki.presenter


import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository

import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons.LessonsPresenter
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons.LessonsView
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons.LessonsViewState
import org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase.LessonsUseCase
import kotlin.test.BeforeTest
import kotlin.test.Test


class LessonsPresenterTest {

    val flashCardsRepository: FlashcardsRepository? = null

    val view: LessonsView = object : LessonsView {
        override fun render(state: LessonsViewState) {

        }

    }

    val lessonsUseCase = LessonsUseCase(flashcardsRepository = flashCardsRepository!!)

    var presenter: LessonsPresenter? = null

    @BeforeTest
    fun setUp() {

        presenter = LessonsPresenter(lessonsUseCase = lessonsUseCase)

        presenter?.attachView(view)

    }

    @Test
    fun testMethod() {

    }

}