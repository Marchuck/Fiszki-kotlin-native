package com.marchuck.fiszki.data

import com.marchuck.fiszki.data.db.*
import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson

class AndroidSpecificFlashCardsRepository(val lessonDao: LessonsDao,
                                          val flashcardsDao: FlashcardsDao) : FlashcardsRepository {

    override fun createLesson(lesson: Lesson, flashcards: List<Flashcard>) {

        lessonDao.insertLesson(RoomLesson(
                lesson.id,
                lesson.translationFrom.code,
                lesson.translationTo.code,
                lesson.name
        ))

        val roomFlashcards = flashcards.map { flashCard -> flashCard.toRoom() }
        flashcardsDao.insertFlashCards(roomFlashcards)
    }

    override fun removeLesson(lesson_id: Long) {
        val flashcards = flashcardsDao.getFlashcards(lesson_id)
        for (f in flashcards) {
            flashcardsDao.deleteFlashcard(f)
        }
        lessonDao.deleteLesson(lesson_id)
    }

    override fun getLesson(lesson_id: Long): Lesson {
        return lessonDao.getLesson(lesson_id).fromRoom()
    }

    override fun getLessons(): List<Lesson> {
        return lessonDao.listAllLessons().map { it.fromRoom() }
    }

    override fun getFlashcards(lesson_id: Long): List<Flashcard> {
        return flashcardsDao.getFlashcards(lesson_id).map { it.fromRoom() }
    }

    override fun updateFlashcards(lesson_id: Long, flashcards: List<Flashcard>) {
        val lesson = lessonDao.getLesson(lesson_id)
        createLesson(lesson.fromRoom(), flashcards)
    }


}