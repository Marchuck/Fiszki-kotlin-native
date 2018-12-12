package com.marchuck.fiszki.data.db

import androidx.room.*


@Dao
interface FlashcardsDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertFlashCards(flashcards: List<RoomFlashcard>)

    @Update
    fun updateFlashcard(flashcard: RoomFlashcard)

    @Delete
    fun deleteFlashcard(flashcard: RoomFlashcard)

    @Query("SELECT * from flashcard WHERE lesson_id LIKE :lesson_id")
    fun getFlashcards(lesson_id: Long): List<RoomFlashcard>



}