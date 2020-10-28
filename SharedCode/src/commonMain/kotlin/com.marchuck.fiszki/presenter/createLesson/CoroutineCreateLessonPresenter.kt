package org.kotlin.mpp.mobile.com.marchuck.fiszki.presenter.createLesson


//class CoroutineCreateLessonPresenter(
//        val createLessonUseCase: CoroutineCreateLessonUseCase,
//        val uiContext: CoroutineContext) : BasePresenter<CreateLessonView>() {
//
//    var pendingLessonId = Random.nextLong(Long.MAX_VALUE)
//    var select_translation_clicks = 0
//    var selectedTranslation: Translation? = null
//    var flashcards: ArrayList<Flashcard> = arrayListOf()
//
//    fun onAddNewFlashcard() {
//        if (selectedTranslation == null) {
//            view?.showTranslationIdEmpty()
//            return
//        }
//        view?.showNewFlashcardForm(selectedTranslation!!)
//    }
//
//    fun putNewFlashcard(heads: String, tails: String) {
//        val flashcard = Flashcard(pendingLessonId, heads, tails, FlashCardState.NOT_SEEN)
//        flashcards.add(flashcard)
//        view?.onFlashcardInserted(flashcard)
//    }
//
//    fun removeFlashcard(flashcard: Flashcard) {
//        flashcards.remove(flashcard)
//    }
//
//    fun onTranslationIdButtonClicked() {
//        if (flashcards.isNotEmpty()) {
//            if (selectedTranslation == null) throw IllegalStateException("you shouldn't face this state ever!!!")
//            else {
//                view?.showCannotSwitchSelectedTranslationBecauseFlashcardsAddedAlready(selectedTranslation!!)
//            }
//            return
//        }
//        val text = getNewTranslationId()
//        view?.showNewTranslationId(text)
//    }
//
//    private fun getNewTranslationId(): String {
//        val values = Translation.values()
//        select_translation_clicks = (select_translation_clicks + 1) % values.size
//        selectedTranslation = values[select_translation_clicks]
//
//        return selectedTranslation!!.name.replace("_", " -> ")
//
//    }
//
//    fun onLessonDoneClicked(lessonName: String): Boolean {
//        view?.clearErrors()
//
//        if (lessonName.isEmpty()) {
//            view?.showLessonNameEmpty()
//            return false
//        }
//
//        if (selectedTranslation == null) {
//            view?.showTranslationIdEmpty()
//            return false
//        }
//        val flashcardsCount = flashcards.size
//        val (min, max) = createLessonUseCase.getFlashcardsRange()
//
//        if (flashcardsCount < min) {
//            view?.showFlashCardsTooLittle(min)
//            return false
//        } else if (flashcardsCount > max) {
//            view?.showFlashCardsTooMuch(max)
//            return false
//        }
//        pendingLessonId
//        val lesson = Lesson(pendingLessonId,
//                Language.recognize(selectedTranslation!!.from),
//                Language.recognize(selectedTranslation!!.to),
//                lessonName)
//
//        view?.showLoading()
//        GlobalScope.launch{
//            createLessonUseCase.createLesson(lesson, flashcards)
//            GlobalScope.launch(uiContext){
//                view?.hideLoading()
//            }
//        }
//        return true
//    }
//}