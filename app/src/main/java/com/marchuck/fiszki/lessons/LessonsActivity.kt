package com.marchuck.fiszki.lessons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.marchuck.fiszki.App
import com.marchuck.fiszki.R
import com.marchuck.fiszki.lessons.learning.LearningActivity
import kotlinx.android.synthetic.main.activity_lessons.*
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons.LessonsPresenter
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons.LessonsView
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.lessons.LessonsViewState
import org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase.LessonsUseCase


class LessonsActivity : AppCompatActivity(), LessonsView {

    val adapter = LessonsAdapter()

    val presenter = LessonsPresenter(LessonsUseCase(App.getFlashCardsRepository()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)

        presenter.attachView(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        adapter.listener = { lesson ->
            presenter.onLessonClick(lesson)
        }
    }

    override fun render(state: LessonsViewState) {
        when (state) {

            is LessonsViewState.Loading -> {
                recyclerView.showShimmerAdapter()
            }

            is LessonsViewState.Content -> {

                adapter.items = state.lessons
                adapter.notifyDataSetChanged()
                recyclerView.hideShimmerAdapter()

            }

            is LessonsViewState.Error -> {

            }

            is LessonsViewState.EnterLesson -> {
                startActivity(LearningActivity.createIntent(this, state.lesson))
            }

        }
    }
}
