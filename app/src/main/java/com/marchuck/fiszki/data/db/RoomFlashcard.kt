package com.marchuck.fiszki.data.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.FlashCardState
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import java.util.*

@Entity(tableName = "flashcard")
data class RoomFlashcard(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id") var id: Long?,
        @ColumnInfo(name = "lesson_id") var lesson_id: Long,
        @ColumnInfo(name = "translation_from") var translationFrom: String,
        @ColumnInfo(name = "translation_to") var translationTo: String,
        @ColumnInfo(name = "flashcard_state") var flashcardState: Int)

fun Flashcard.toRoom() = RoomFlashcard(
        UUID.randomUUID().toString().hashCode().toLong(),
        lesson_id,
        heads,
        tails,
        flashCardState.code)

fun RoomFlashcard.fromRoom() = Flashcard(lesson_id, translationFrom, translationTo, FlashCardState.fromCode(flashcardState))