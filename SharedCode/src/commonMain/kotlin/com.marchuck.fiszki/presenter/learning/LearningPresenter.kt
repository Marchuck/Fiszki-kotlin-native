package com.marchuck.fiszki.presenter.learning

import org.kotlin.mpp.mobile.com.marchuck.fiszki.FlashcardsRepository
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.FlashCardState
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.base.BasePresenter
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.learning.FlashcardViewState
import org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.learning.LearningView

class LearningPresenter(val flashcardRepository: FlashcardsRepository) : BasePresenter<LearningView>() {

    var flashcards: ArrayList<Flashcard> = arrayListOf()
    var lesson_id: Long = 0
    var position = 0
    var rounds = 1

    var wrongs: List<Flashcard> = arrayListOf()
    var skipped: List<Flashcard> = arrayListOf()

    fun startWithLesson(lesson_id: Long) {

        this.lesson_id = lesson_id

        val lesson = flashcardRepository.getLesson(lesson_id)
        flashcards = arrayListOf()
        flashcards.addAll(flashcardRepository.getFlashcards(lesson_id))

        position = 0

        view?.render(FlashcardViewState.TITLE(lesson.name))

        view?.render(FlashcardViewState.BEFORE_REVEAL(position, flashcards[position]))
    }

    fun pushState(state: FlashCardState) {
        this.flashcards[position] = flashcards[position].withState(state)

        if (position == flashcards.size - 1) {

            wrongs = flashcards.filter { it.flashCardState == FlashCardState.WRONG }
            skipped = flashcards.filter { it.flashCardState == FlashCardState.SKIPPED }
            val ok = flashcards.filter { it.flashCardState == FlashCardState.OK }

            if (wrongs.isEmpty() && skipped.isEmpty()) {
                view?.render(FlashcardViewState.FINISHED_LESSON(rounds))
            } else {
                view?.render(FlashcardViewState.REACHED_END(wrongs, skipped, ok, flashcards.size))
            }
        } else {
            ++position
            view?.render(FlashcardViewState.BEFORE_REVEAL(position, flashcards[position]))
        }
    }

    fun generateNewRound() {
        ++rounds
        val list = arrayListOf<Flashcard>()
        list.addAll(wrongs)
        list.addAll(skipped)
        list.shuffle()
        this.flashcards = list

        position = 0
        view?.render(FlashcardViewState.BEFORE_REVEAL(position, flashcards[position]))
    }

    fun revealCard(){
        view?.render(FlashcardViewState.AFTER_REVEAL)
    }
}


