package com.marchuck.fiszki.lessons.learning

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.marchuck.fiszki.App
import com.marchuck.fiszki.R
import kotlinx.android.synthetic.main.activity_learning.*
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.FlashCardState
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson
import java.util.*

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
    var rounds = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        lesson_id = intent.getLongExtra(LESSON_ID, -1)

        val lesson = App.getFlashCardsRepository().getLesson(lesson_id)
        flashcards = arrayListOf()
        flashcards.addAll(App.getFlashCardsRepository().getFlashcards(lesson_id))

        val languageFrom = lesson.translationFrom
        val languageTo = lesson.translationTo

        supportActionBar?.title = lesson.name

        position = 0
        renderFlashCardState(FlashcardViewState.BEFORE_REVEAL)

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

    private fun renderFlashCardState(flashcardViewState: FlashcardViewState) {
        when (flashcardViewState) {
            is FlashcardViewState.BEFORE_REVEAL -> {
                displayNewFlashcard(position)
                hideButtons()
            }
            is FlashcardViewState.AFTER_REVEAL -> {
                showButtons()
            }
            is FlashcardViewState.REACHED_END -> {
                showCurrentSummary()
            }
            is FlashcardViewState.FINISHED_LESSON -> {

                AlertDialog.Builder(this)
                        .setTitle("Great job!")
                        .setCancelable(false)
                        .setMessage("You answered all flashcards in $rounds rounds!!!")
                        .setPositiveButton("back") { dialog, which ->
                            finish()
                        }.show()
            }
        }
    }

    private fun hideButtons() {
        button_known.visibility = View.GONE
        button_wrong.visibility = View.GONE
        button_skip.visibility = View.GONE
    }

    private fun showButtons() {
        button_known.visibility = View.VISIBLE
        button_wrong.visibility = View.VISIBLE
        button_skip.visibility = View.VISIBLE
    }

    fun proceedNormally() {
        if (position == flashcards.size - 1) {
            showCurrentSummary()
        } else {
            ++position
            renderFlashCardState(FlashcardViewState.BEFORE_REVEAL)
        }
    }

    fun proceedWithState(state: FlashCardState) {
        getTopFragment()?.let {
            this.flashcards[position] = flashcards[position].withState(state)
            proceedNormally()
        }
    }

    private fun showCurrentSummary() {
        val wrongs = flashcards.filter { it.flashCardState == FlashCardState.WRONG }
        val skipped = flashcards.filter { it.flashCardState == FlashCardState.SKIPPED }
        val ok = flashcards.filter { it.flashCardState == FlashCardState.OK }

        if (wrongs.isEmpty() && skipped.isEmpty()) {
            renderFlashCardState(FlashcardViewState.FINISHED_LESSON(flashcards.size))
        } else {

            AlertDialog.Builder(this)
                    .setTitle("Your progress")
                    .setMessage("You answered ${ok.size} of ${flashcards.size}, ${wrongs.size} wrong and ${skipped.size} skipped. Do you wish to continue?")
                    .setCancelable(false)
                    .setPositiveButton("continue") { dialog, which ->
                        ++rounds
                        val list = arrayListOf<Flashcard>()
                        list.addAll(wrongs)
                        list.addAll(skipped)
                        list.shuffle()
                        this.flashcards = list

                        position = 0
                        renderFlashCardState(FlashcardViewState.BEFORE_REVEAL)
                    }
                    .setNegativeButton("exit") { dialog, which ->
                        finish()
                    }.show()
        }
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

    fun reveal() {
        renderFlashCardState(FlashcardViewState.AFTER_REVEAL)
    }
}

private fun Flashcard.withState(state: FlashCardState): Flashcard {
    return Flashcard(lesson_id, heads, tails, state)
}

sealed class FlashcardViewState {
    object BEFORE_REVEAL : FlashcardViewState()
    object AFTER_REVEAL : FlashcardViewState()
    object REACHED_END : FlashcardViewState()
    data class FINISHED_LESSON(val flashcardsSize: Int) : FlashcardViewState()
}