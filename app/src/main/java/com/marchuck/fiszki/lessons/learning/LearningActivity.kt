package com.marchuck.fiszki.lessons.learning

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marchuck.fiszki.App
import com.marchuck.fiszki.R
import kotlinx.android.synthetic.main.activity_learning.*
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.FlashCardState
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
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

    var lesson_id: Long = -1
    var flashcards: ArrayList<Flashcard> = arrayListOf()
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        lesson_id = intent.getLongExtra(LESSON_ID, -1)

        val lesson = App.getFlashCardsRepository().getLesson(lesson_id)
        val flashcards = App.getFlashCardsRepository().getFlashcards(lesson_id)

        val languageFrom = lesson.translationFrom
        val languageTo = lesson.translationTo

        displayNewFlashcard(0)

        button_known.setOnClickListener { view ->
            proceedWithState(FlashCardState.OK)
        }
        button_wrong.setOnClickListener { view ->
            proceedWithState(FlashCardState.WRONG)
        }
        button_skip.setOnClickListener { view ->
            proceedNormally()
        }

    }

    fun proceedNormally() {

        if (position == flashcards.size - 1) {
            showCurrentSummary()
        } else {
            displayNewFlashcard(++position)
        }
    }

    fun proceedWithState(state: FlashCardState) {
        getTopFragment()?.let {
            if (!it.isInRevealedState()) {
                it.reveal()
            } else {
                this.flashcards[position] = flashcards[position].withState(state)

                proceedNormally()
            }
        }
    }

    private fun showCurrentSummary() {

    }


    fun displayNewFlashcard(position: Int) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, FlashCardFragment.newInstance(flashcards[position].toParcel()))
                .commitAllowingStateLoss()

    }

    fun getTopFragment(): FlashCardFragment? {
        val frags = supportFragmentManager.fragments
        for (f in frags) {
            if (f is FlashCardFragment) {
                return f
            }
        }
        return null
    }
}

private fun Flashcard.withState(state: FlashCardState): Flashcard {
    return Flashcard(lesson_id, heads, tails, state)
}
