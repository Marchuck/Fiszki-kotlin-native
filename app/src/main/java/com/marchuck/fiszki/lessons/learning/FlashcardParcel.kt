package com.marchuck.fiszki.lessons.learning

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard

@Parcelize
data class FlashcardParcel(val state: Int,
                           val heads: String,
                           val tails: String,
                           val imageUrl: String,
                           val lesson_id: String) : Parcelable {


}

fun Flashcard.toParcel() = FlashcardParcel(flashCardState.code,
        heads,tails,image,lesson_id)