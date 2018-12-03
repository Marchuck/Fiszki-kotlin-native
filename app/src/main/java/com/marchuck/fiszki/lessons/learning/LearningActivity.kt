package com.marchuck.fiszki.lessons.learning

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.marchuck.fiszki.R

class LearningActivity : AppCompatActivity() {

    companion object {

        val LESSON = "LESSON"

        fun createIntent(context: Context, lesson: String): Intent {
            val intent = Intent(context, LearningActivity::class.java)
            intent.putExtra(LESSON, lesson)
            return intent
        }
    }

    lateinit var lesson: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        lesson = intent.getStringExtra(LESSON)


    }
}
