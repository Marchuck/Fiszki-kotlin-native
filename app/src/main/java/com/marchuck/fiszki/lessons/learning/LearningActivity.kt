package com.marchuck.fiszki.lessons.learning

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marchuck.fiszki.R
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson

class LearningActivity : AppCompatActivity() {

    companion object {

        val LESSON_ID = "LESSON_ID"

        fun createIntent(context: Context, lesson: Lesson): Intent {
            val intent = Intent(context, LearningActivity::class.java)
            intent.putExtra(LESSON_ID, lesson.id)
            return intent
        }
    }

    var lesson: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        lesson = intent.getLongExtra(LESSON_ID, -1)


    }
}
