package com.marchuck.fiszki.lessons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jetbrains.jonnyzzz.myapplication.R
import kotlinx.android.synthetic.main.activity_lessons.*
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.LessonsView
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.LessonsViewState

class LessonsActivity : AppCompatActivity(), LessonsView {

    val adapter = LessonsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
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

            }
        }
    }

}
