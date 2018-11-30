package org.kotlin.mpp.mobile.com.marchuck.fiszki.model

data class Flashcard(val topic_id: String,
                     val translation_id: String,
                     val image: String,
                     val heads: String,
                     val tails: String)