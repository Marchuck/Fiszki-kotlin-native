package com.marchuck.fiszki

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.marchuck.fiszki.lessons.LessonsActivity
import com.marchuck.fiszki.new_lesson.CreateLessonActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lessons.setOnClickListener {
            startOut(LessonsActivity::class.java)
        }
        add_new_lesson.setOnClickListener {
            startOut(CreateLessonActivity::class.java)
        }
        settings.setOnClickListener {
            // startOut(SettingsActivity::class.java)
            showToast("Soon...")
        }

    }

    private fun showToast(s: String) {
        Toast.makeText(this.applicationContext, s, Toast.LENGTH_LONG).show()
    }

    private fun startOut(klazz: Class<*>) {
        val intent = Intent(this, klazz)
        startActivity(intent)
    }
}
