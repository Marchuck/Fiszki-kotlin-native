//
//  CreateLessonViewController.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 07.12.2018.
//  Copyright © 2018 Evgeny Petrenko. All rights reserved.
//

import UIKit

import SharedCode

class CreateLessonViewController: UIViewController, CreateLessonView {
    
    let presenter = CreateLessonPresenter(createLessonUseCase: CreateLessonUseCase(flashcardsRepository: IOSFlashcardsRepository.INSTANCE))
    
    var flashcards : [Flashcard] = []
   
    func showLessonNameEmpty() {
        showAbsorbingDialog("Please enter lesson name")
    }
    
    func showTranslationIdEmpty() {
        showAbsorbingDialog("Please select languages of translation")
    }
    
    func showFlashCardsTooLittle(min: Int32) {
        showAbsorbingDialog("Sorry, you need to enter at least \(min) flashcards")
    }
    
    func showFlashCardsTooMuch(max: Int32) {
    
        showAbsorbingDialog("Sorry, you reached max(\(max)) number of flashcards ")
    }
    
    func clearErrors() {
        //no-op
    }
    
    func showNewFlashcardForm(translationId: Translation) {
        print("showNewFlashcardForm")
        let heads   = "from \(translationId.from) : \(flashcards.count)"
        let tails   = "to \(translationId.to) : \(flashcards.count)"
        presenter.putNewFlashcard(heads: heads, tails: tails)
    }
    
    func showNewTranslationId(text: String) {
            selectedTranslation.text = text
    }
    
    func showCannotSwitchSelectedTranslationBecauseFlashcardsAddedAlready(selectedTranslationId: Translation) {
        showAbsorbingDialog("Sorry, but you already added some translations for \(selectedTranslationId.name).")
    }
    
    func onFlashcardInserted(flashcard: Flashcard) {
        self.flashcards.append(flashcard)
        self.addedFlashcardsTableView.reloadData()
    }
    
    @IBOutlet weak var lessonNameTextField: UITextField!
   
    @IBOutlet weak var selectedTranslation: UILabel!
    
    @IBOutlet weak var addedFlashcardsTableView: UITableView!
    
    @IBAction func selectTranslationButtonClicked(_ sender: Any) {
        presenter.onTranslationIdButtonClicked()
    }
    
    @IBAction func newFlashcardButtonClicked(_ sender: Any) {
        presenter.onAddNewFlashcard()
    }
    
    @IBAction func doneButtonClicked(_ sender: Any) {
        showAbsorbingDialog("DONE")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        presenter.attachView(view: self)

        configureTableView()
    }
    
    func configureTableView(){
        addedFlashcardsTableView.rowHeight = UITableView.automaticDimension
        addedFlashcardsTableView.estimatedRowHeight = 120.0
        addedFlashcardsTableView.dataSource = self
    }
    
 
    private func showAbsorbingDialog(_ message: String){
        // create the alert
        let alert = UIAlertController(title: "Warning", message: message, preferredStyle: UIAlertController.Style.alert)
        
        // add an action (button)
        alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
        
        // show the alert
        self.present(alert, animated: true, completion: nil)
    }
}

extension CreateLessonViewController:  UITableViewDelegate, UITableViewDataSource {
   
    func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return flashcards.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "flashcardCell", for: indexPath) as UITableViewCell
        
        if cell is FlashcardTableViewCell{
            let flashcardCell = cell as! FlashcardTableViewCell
            
            let flashcard = flashcards[indexPath.row]
            
            flashcardCell.bind(flashcard: flashcard, listener: { card in
                self.presenter.removeFlashcard(flashcard: card)
            })
        }
    
        return cell
    }
}
