package org.kotlin.mpp.mobile.com.marchuck.fiszki.model

data class Flashcard(val lesson_id: String,
                     val image: String,
                     val heads: String,
                     val tails: String,
                     val flashCardState: FlashCardState) {

}

fun Flashcard.resetProgress(): Flashcard {
    return Flashcard(lesson_id,
            image,
            heads,
            tails,
            FlashCardState.NOT_SEEN)
}