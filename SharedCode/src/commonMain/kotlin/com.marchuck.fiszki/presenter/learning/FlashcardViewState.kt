package org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.learning

import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard

sealed class FlashcardViewState {
    data class TITLE(val title: String) : FlashcardViewState()

    data class BEFORE_REVEAL(val position: Int, val flashcard: Flashcard) : FlashcardViewState()

    object AFTER_REVEAL : FlashcardViewState()

    data class REACHED_END(val wrong: List<Flashcard>,
                           val skipped: List<Flashcard>,
                           val learned: List<Flashcard>,
                           val size: Int) : FlashcardViewState()

    data class FINISHED_LESSON(val rounds: Int) : FlashcardViewState()
}