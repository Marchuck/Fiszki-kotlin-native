package com.marchuck.fiszki.lessons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jetbrains.jonnyzzz.myapplication.R
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.LessonsView
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.LessonsViewState

class LessonsActivity : AppCompatActivity(), LessonsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)
    }

    override fun render(state: LessonsViewState) {
        when (state) {
            is LessonsViewState.Loading -> {

            }
            is LessonsViewState.Content -> {

            }
            is LessonsViewState.Error -> {

            }
            is LessonsViewState.EnterLesson -> {

            }
        }
    }

}
