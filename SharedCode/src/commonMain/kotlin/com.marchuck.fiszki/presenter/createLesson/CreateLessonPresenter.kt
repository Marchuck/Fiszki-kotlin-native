package org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.createLesson

import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.FlashCardState
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.TranslationId
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.base.BasePresenter
import org.kotlin.mpp.mobile.com.marchuck.fiszki.useCase.CreateLessonUseCase

class CreateLessonPresenter(val createLessonUseCase: CreateLessonUseCase) : BasePresenter<CreateLessonView>() {

    var select_translation_clicks = 0
    var selectedTranslationId: TranslationId? = null
    var flashcards: ArrayList<Flashcard> = arrayListOf()

    fun onAddNewFlashcard() {
        if (selectedTranslationId == null) {
            view?.showTranslationIdEmpty()
            return
        }
        view?.showNewFlashcardForm(selectedTranslationId!!)
    }

    fun putNewFlashcard(heads: String, tails: String) {
        val flashcard = Flashcard("", "", heads, tails, FlashCardState.NOT_SEEN)
        flashcards.add(flashcard)
        view?.onFlashcardInserted(flashcard)
    }

    fun removeFlashcard(flashcard: Flashcard) {
        flashcards.remove(flashcard)
    }

    fun onTranslationIdButtonClicked() {
        if (flashcards.isNotEmpty()) {
            if (selectedTranslationId == null) throw IllegalStateException("you shouldn't face this state ever!!!")
            else {
                view?.showCannotSwitchSelectedTranslationBecauseFlashcardsAddedAlready(selectedTranslationId!!)
            }
            return
        }
        val text = getNewTranslationId()
        view?.showNewTranslationId(text)
    }

    private fun getNewTranslationId(): String {
        val values = TranslationId.values()
        select_translation_clicks = (select_translation_clicks + 1) % values.size
        selectedTranslationId = values[select_translation_clicks]

        return selectedTranslationId!!.name.replace("_", " -> ")

    }

    fun onLessonDoneClicked(lessonName: String): Boolean {
        view?.clearErrors()

        if (lessonName.isEmpty()) {
            view?.showLessonNameEmpty()
            return false
        }
        if (selectedTranslationId == null) {
            view?.showTranslationIdEmpty()
            return false
        }
        val flashcardsCount = flashcards.size
        val (min, max) = createLessonUseCase.getFlashcardsRange()

        if (flashcardsCount < min) {
            view?.showFlashCardsTooLittle(min)
            return false
        } else if (flashcardsCount > max) {
            view?.showFlashCardsTooMuch(max)
            return false
        }
        createLessonUseCase.createLesson(lessonName, selectedTranslationId!!, flashcards)
        return true
    }
}