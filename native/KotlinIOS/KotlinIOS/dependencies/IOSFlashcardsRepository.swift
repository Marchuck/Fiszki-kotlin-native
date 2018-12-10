//
//  IOSFlashcardsRepository.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 29.11.2018.
//  Copyright © 2018 Evgeny Petrenko. All rights reserved.
//

import Foundation
import SharedCode

class IOSFlashcardsRepository: NSObject, FlashcardsRepository{
    
    let lessons = [
        Lesson(id: 1, translationFrom: Language.english, translationTo: Language.german, name: "nature"),
        Lesson(id: 2, translationFrom: Language.english, translationTo: Language.german, name: "sky"),
        Lesson(id: 3, translationFrom: Language.english, translationTo: Language.german, name: "school"),
        Lesson(id: 4, translationFrom: Language.english, translationTo: Language.german, name: "holiday"),
        Lesson(id: 5, translationFrom: Language.english, translationTo: Language.german, name: "city"),
        Lesson(id: 6, translationFrom: Language.english, translationTo: Language.german, name: "books"),
        ]
    let flashcards = [
        Flashcard(lesson_id: 1, heads: "Cat", tails: "Die Katze", flashCardState: FlashCardState.notSeen),
        Flashcard(lesson_id: 2, heads: "Dog", tails: "Der Hund", flashCardState: FlashCardState.notSeen),
        Flashcard(lesson_id: 3, heads: "Duck", tails: "Die Ente", flashCardState: FlashCardState.notSeen),
        Flashcard(lesson_id: 4, heads: "Fish", tails: "Das Fisch", flashCardState: FlashCardState.notSeen),
        Flashcard(lesson_id: 5, heads: "house", tails: "Haus", flashCardState: FlashCardState.notSeen),
        
            ]
    
    let repository: [ Lesson: [Flashcard]] = [ : ]
    
    func createLesson(lesson: Lesson, flashcards: [Flashcard]) {
        
    }
    
    func removeLesson(lesson_id: Int64) {
        
    }
    
    func getLesson(lesson_id: Int64) -> Lesson {
        return lessons[0]
    }
    
    func getLessons() -> [Lesson] {
        return lessons
    }
    
    func getFlashcards(lesson_id: Int64) -> [Flashcard] {
        return flashcards
    }
    
    func updateFlashcards(lesson_id: Int64, flashcards: [Flashcard]) {
        
    }
    
   
    static let INSTANCE :  FlashcardsRepository = IOSFlashcardsRepository()
    

    
}
