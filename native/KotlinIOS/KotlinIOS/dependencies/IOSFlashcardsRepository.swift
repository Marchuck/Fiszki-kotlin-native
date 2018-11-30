//
//  IOSFlashcardsRepository.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 29.11.2018.
//  Copyright © 2018 Evgeny Petrenko. All rights reserved.
//

import Foundation
import SharedCode

class IOSFlashcardsRepository: FlashcardsRepository{
   
    static let INSTANCE :  FlashcardsRepository = IOSFlashcardsRepository()
    
    
    let lessons : [String:  [Flashcard] ] = [
//        "1" : [
//            Flashcard(key: "1", image: "", heads: "book", tails: "książka"),
//            Flashcard(key: "2", image: "", heads: "cat", tails: "kot"),
//            Flashcard(key: "3", image: "", heads: "fish", tails: "ryba"),
//            Flashcard(key: "4", image: "", heads: "dog", tails: "pies"),
//        ]
        :
    ]
    
    
    func getLesson(lesson: String) -> [Flashcard] {
        return []
    }
    
    func resetProgress(lesson: String) {
        
    }
    
    func updateProgress(lesson: String, knowledgeProgress: KnowledgeProgress) {
        
    }
    
    func getFlashCardLessons() -> [String] {
        return []
    }
    
    
}
