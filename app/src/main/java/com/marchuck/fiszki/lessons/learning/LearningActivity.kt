package com.marchuck.fiszki.lessons.learning

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.marchuck.fiszki.R
import kotlinx.android.synthetic.main.activity_learning.*
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.FlashCardState
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson

class LearningActivity : AppCompatActivity() {

    companion object {

        const val LESSON_ID = "LESSON_ID"

        fun createIntent(context: Context, lesson: Lesson): Intent {
            val intent = Intent(context, LearningActivity::class.java)
            intent.putExtra(LESSON_ID, lesson.id)
            return intent
        }
    }

    var lesson_id: Long = -1

    val presenter = LearningPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        val lesson_id = intent.getLongExtra(LESSON_ID, -1)

        presenter.attachView(this)

        presenter.startWithLesson(lesson_id)

        button_known.setOnClickListener { view ->
            presenter.pushState(FlashCardState.OK)
        }
        button_wrong.setOnClickListener { view ->
            presenter.pushState(FlashCardState.WRONG)
        }
        button_skip.setOnClickListener { view ->
            presenter.pushState(FlashCardState.SKIPPED)
        }
    }

    fun render(state: FlashcardViewState) {
        when (state) {
            is FlashcardViewState.BEFORE_REVEAL -> {
                displayNewFlashcard(state.flashcard)
                hideButtons()
            }
            is FlashcardViewState.AFTER_REVEAL -> {
                showButtons()
            }
            is FlashcardViewState.REACHED_END -> {
                showCurrentSummary(state)
            }
            is FlashcardViewState.FINISHED_LESSON -> {
                showFinishSummary(state.rounds)
            }
            is FlashcardViewState.TITLE -> {
                supportActionBar?.title = state.title
            }
        }
    }

    private fun showFinishSummary(rounds: Int) {
        AlertDialog.Builder(this)
                .setTitle("Great job!")
                .setCancelable(false)
                .setMessage("You answered all flashcards in $rounds rounds!!!")
                .setPositiveButton("back") { dialog, which ->
                    finish()
                }.show()
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

    private fun showCurrentSummary(state: FlashcardViewState.REACHED_END) {
        val wrongs = state.wrong
        val skipped = state.skipped
        val learned = state.learned

        val allFlashcardsSize = wrongs.size + skipped.size + learned.size

        AlertDialog.Builder(this)
                .setTitle("Your progress")
                .setMessage("You answered ${learned.size} of $allFlashcardsSize," +
                        " ${wrongs.size} wrong and ${skipped.size} skipped. Do you wish to continue?")
                .setCancelable(false)
                .setPositiveButton("continue") { dialog, which ->
                    presenter.generateNewRound()
                }
                .setNegativeButton("exit") { dialog, which ->
                    finish()
                }.show()
    }

    private fun displayNewFlashcard(flashcard: Flashcard) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, FlashCardFragment.newInstance(flashcard.toParcel()))
                .commitAllowingStateLoss()
    }

    fun revealFlashcard() {
        render(FlashcardViewState.AFTER_REVEAL)
    }
}

fun Flashcard.withState(state: FlashCardState): Flashcard {
    return Flashcard(lesson_id, heads, tails, state)
}

sealed class FlashcardViewState {
    data class TITLE(val title: String) : FlashcardViewState()

    data class BEFORE_REVEAL(val position: Int, val flashcard: Flashcard) : FlashcardViewState()

    object AFTER_REVEAL : FlashcardViewState()

    data class REACHED_END(val wrong: List<Flashcard>,
                           val skipped: List<Flashcard>,
                           val learned: List<Flashcard>) : FlashcardViewState()

    data class FINISHED_LESSON(val rounds: Int) : FlashcardViewState()
}