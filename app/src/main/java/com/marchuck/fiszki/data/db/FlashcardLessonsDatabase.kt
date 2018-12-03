package com.marchuck.fiszki.data.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomLesson::class, RoomFlashcard::class), version = 2)
abstract class FlashcardLessonsDatabase : RoomDatabase() {

    abstract fun getFlashcardsDao(): FlashcardsDao
    abstract fun getLessonsDao(): LessonsDao

    companion object {
        private var INSTANCE: FlashcardLessonsDatabase? = null

        fun getInstance(context: Context): FlashcardLessonsDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(context.applicationContext, FlashcardLessonsDatabase::class.java,
                        "flashcardLessonsDatabase.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE!!
        }
    }

}