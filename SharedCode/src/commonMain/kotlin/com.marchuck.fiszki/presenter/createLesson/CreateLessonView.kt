package org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.createLesson

import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Translation

interface CreateLessonView {

    fun showLessonNameEmpty()

    fun showTranslationIdEmpty()

    fun showFlashCardsTooLittle(min: Int)

    fun showFlashCardsTooMuch(max: Int)

    fun clearErrors()

    fun showNewFlashcardForm(translationId: Translation)

    fun showNewTranslationId(text: String)

    fun showCannotSwitchSelectedTranslationBecauseFlashcardsAddedAlready(selectedTranslationId: Translation)

    fun onFlashcardInserted(flashcard: Flashcard)

}