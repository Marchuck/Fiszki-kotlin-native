//
//  IOSFlashcardsRepository.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 29.11.2018.
//

import Foundation
import SharedCode

class IOSFlashcardsRepository: NSObject, FlashcardsRepository {
    
    static let INSTANCE :  FlashcardsRepository = IOSFlashcardsRepository()
    
    var lessons : [Int64: Lesson]  = [
         1: Lesson(id: 1, translationFrom: Language.english, translationTo: Language.german, name: "nature"),
         2: Lesson(id: 2, translationFrom: Language.english, translationTo: Language.german, name: "sky"),
         3: Lesson(id: 3, translationFrom: Language.english, translationTo: Language.german, name: "school"),
         4: Lesson(id: 4, translationFrom: Language.english, translationTo: Language.german, name: "holiday"),
         5: Lesson(id: 5, translationFrom: Language.english, translationTo: Language.german, name: "city"),
         6: Lesson(id: 6, translationFrom: Language.english, translationTo: Language.german, name: "books")
    ]
    
    var flashcards : [Int64: [Flashcard] ] = [
        1: [
            Flashcard(lesson_id: 1, heads: "house", tails: "haus", flashCardState:FlashCardState.notSeen),
            Flashcard(lesson_id: 1, heads: "cat", tails: "die Katze", flashCardState:FlashCardState.notSeen),
            Flashcard(lesson_id: 1, heads: "dog", tails: "der Hund", flashCardState:FlashCardState.notSeen),
            Flashcard(lesson_id: 1, heads: "duck", tails: "die Ente", flashCardState:FlashCardState.notSeen),
            Flashcard(lesson_id: 1, heads: "fish", tails: "das Fisch", flashCardState:FlashCardState.notSeen),
        ],
        2: [
            Flashcard(lesson_id: 2, heads: "house", tails: "haus", flashCardState:FlashCardState.notSeen),
            Flashcard(lesson_id: 2, heads: "cat", tails: "die Katze", flashCardState:FlashCardState.notSeen),
            Flashcard(lesson_id: 2, heads: "dog", tails: "der Hund", flashCardState:FlashCardState.notSeen),
            Flashcard(lesson_id: 2, heads: "duck", tails: "die Ente", flashCardState:FlashCardState.notSeen),
            Flashcard(lesson_id: 2, heads: "fish", tails: "das Fisch", flashCardState:FlashCardState.notSeen),
        ]
        
    ]
    
    let repository: [ Lesson: [Flashcard]] = [ : ]
    
    func createLesson(lesson: Lesson, flashcards: [Flashcard]) {
        self.lessons[lesson.id] = lesson
        self.flashcards[lesson.id] = flashcards
    }
    
    func removeLesson(lesson_id: Int64) {
        self.lessons[lesson_id] = nil
        self.flashcards[lesson_id] = nil
    }
    
    func getLesson(lesson_id: Int64) -> Lesson {
        let lesson = lessons[lesson_id]
        if lesson != nil {
            return lesson!
        } else {
            return Lesson(id: -1)
        }
    }
    
    func getLessons() -> [Lesson] {
        return lessons.map({$0.value})
    }
    
    func getFlashcards(lesson_id: Int64) -> [Flashcard] {
        let flashes: [Flashcard] =  flashcards[lesson_id] ?? []
        return flashes
    }
    
    func updateFlashcards(lesson_id: Int64, flashcards: [Flashcard]) {
        
    }
}
