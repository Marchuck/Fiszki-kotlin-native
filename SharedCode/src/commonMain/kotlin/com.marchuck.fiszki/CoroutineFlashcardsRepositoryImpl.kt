package org.kotlin.mpp.mobile.com.marchuck.fiszki

//class CoroutineFlashcardsRepositoryImpl : CoroutineFlashcardsRepository {
//
//    val DURATION = 500L
//
//    override suspend fun createLesson(lesson: Lesson, flashcards: List<Flashcard>): Lesson {
//        delay(DURATION)
//        return Lesson(16)
//    }
//
//    override suspend fun removeLesson(lesson_id: Long): Lesson {
//        delay(DURATION)
//        return Lesson(15)
//    }
//
//    override suspend fun getLesson(lesson_id: Long): Lesson {
//        delay(DURATION)
//        return Lesson(12, Language.GERMAN, Language.ENGLISH, "lesson12")
//    }
//
//    override suspend fun getLessons(): List<Lesson> {
//        delay(DURATION)
//        return arrayListOf(
//                Lesson(12, Language.GERMAN, Language.ENGLISH, "lesson12"),
//                Lesson(13, Language.GERMAN, Language.POLISH, "lesson13")
//        )
//    }
//
//    override suspend fun getFlashcards(lesson_id: Long): List<Flashcard> {
//        delay(DURATION)
//        return arrayListOf(
//                Flashcard(12L, "a", "b", FlashCardState.NOT_SEEN),
//                Flashcard(13L, "c", "d", FlashCardState.NOT_SEEN)
//        )
//    }
//
//    override suspend fun updateFlashcards(lesson_id: Long, flashcards: List<Flashcard>): Lesson {
//        delay(DURATION)
//        return Lesson(24, Language.GERMAN, Language.ENGLISH, "lesson12")
//    }
//}