package com.marchuck.fiszki.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LessonsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLesson(lesson: RoomLesson)

    @Query("DELETE FROM lesson WHERE id = :lesson_id")
    fun deleteLesson(lesson_id: Long)

    @Query("SELECT * FROM lesson WHERE id = :lesson_id LIMIT 1")
    fun getLesson(lesson_id: Long): RoomLesson

    @Query("SELECT * FROM lesson")
    fun listAllLessons(): List<RoomLesson>


}