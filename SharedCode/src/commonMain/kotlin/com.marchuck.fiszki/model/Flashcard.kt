package org.kotlin.mpp.mobile.com.marchuck.fiszki.model

data class Flashcard(val lesson_id: Long,
                     val heads: String,
                     val tails: String,
                     val flashCardState: FlashCardState) {

    fun withState(state: FlashCardState): Flashcard {
        return Flashcard(lesson_id,heads,tails,state)
    }

}

fun Flashcard.resetProgress(): Flashcard {
    return Flashcard(lesson_id,
            heads,
            tails,
            FlashCardState.NOT_SEEN)
}