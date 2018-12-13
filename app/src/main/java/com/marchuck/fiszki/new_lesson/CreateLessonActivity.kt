package com.marchuck.fiszki.new_lesson

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.marchuck.fiszki.App
import com.marchuck.fiszki.R
import kotlinx.android.synthetic.main.activity_create_lesson.*
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Translation
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.createLesson.CreateLessonPresenter
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.createLesson.CreateLessonView
import org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase.CreateLessonUseCase


class CreateLessonActivity : AppCompatActivity(), CreateLessonView {
    override fun hideLoading() {

    }

    override fun showLoading() {

    }

    val adapter = FlashcardsAdapter()

    override fun showLessonNameEmpty() {
        lesson_name_layout.error = "Please enter lesson name"
    }

    override fun showTranslationIdEmpty() {
        showAbsorbingDialog("Please select languages of translation")
    }

    override fun showFlashCardsTooLittle(min: Int) {
        showAbsorbingDialog("Sorry, you need to enter at least $min flashcards")
    }

    override fun showFlashCardsTooMuch(max: Int) {
        showAbsorbingDialog("Sorry, you reached max($max) number of flashcards ")
    }

    override fun clearErrors() {
        lesson_name_layout.error = null
    }

    override fun onFlashcardInserted(flashcard: Flashcard) {
        adapter.items.add(flashcard)
        adapter.notifyItemInserted(adapter.items.indexOf(flashcard))
    }

    override fun showNewFlashcardForm(translationId: Translation) {
        hideKeyboard()
        val fm = supportFragmentManager

        val (heads, tails) = translationId.name.split("_")

        val fragment = InsertFlashcardDialogFragment.newInstance(
                heads, tails
        )
        fragment.show(fm, "insert_flashcard")
    }

    override fun showNewTranslationId(text: String) {
        selected_translation.text = text
    }

    override fun showCannotSwitchSelectedTranslationBecauseFlashcardsAddedAlready(selectedTranslationId: Translation) {
        showAbsorbingDialog("Sorry, but you already added some translations for ${selectedTranslationId.name} .")
    }

    private fun showAbsorbingDialog(message: String) {
        AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                .create()
                .show()
    }

    val presenter = CreateLessonPresenter(CreateLessonUseCase(App.getFlashCardsRepository()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_lesson)

        presenter.attachView(this)

        done.setOnClickListener {
            val result = presenter.onLessonDoneClicked(lesson_name_edit.text.toString().trim())

            if (result) {
                Handler(Looper.getMainLooper()).postDelayed({
                    finish()
                }, 300)
            }
        }

        add_translation.setOnClickListener {
            presenter.onAddNewFlashcard()
        }

        select_translation.setOnClickListener {
            presenter.onTranslationIdButtonClicked()
        }

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
        adapter.listener = object : FlashcardsAdapter.Listener {
            override fun onDelete(item: Flashcard, position: Int) {
                presenter.removeFlashcard(item)
                adapter.notifyItemRemoved(position)
            }
        }
    }

    fun hideKeyboard() {
        val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = this.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
