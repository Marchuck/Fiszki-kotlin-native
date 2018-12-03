package org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.createLesson

import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.TranslationId

interface CreateLessonView {

    fun showLessonNameEmpty()

    fun showTranslationIdEmpty()

    fun showFlashCardsTooLittle(min: Int)

    fun showFlashCardsTooMuch(max: Int)

    fun clearErrors()

    fun showNewFlashcardForm(translationId: TranslationId)

    fun showNewTranslationId(text: String)

    fun showCannotSwitchSelectedTranslationBecauseFlashcardsAddedAlready(selectedTranslationId: TranslationId)

    fun onFlashcardInserted(flashcard: Flashcard)

}