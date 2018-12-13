//
//  LearningViewController.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 10.12.2018.
//

import UIKit

import SharedCode

class LearningViewController: UIViewController, LearningView {
    
    let presenter = LearningPresenter(flashcardRepository: IOSFlashcardsRepository.INSTANCE)

    var flashcard : Flashcard? = nil
    
    @IBOutlet weak var headsButton: UIButton!
    @IBOutlet weak var tailsButton: UIButton!

    @IBOutlet weak var knowButton: UIButton!
    @IBOutlet weak var dontKnowButton: UIButton!
    @IBOutlet weak var skipButton: UIButton!
    
    @IBAction func headsButtonClicked(_ sender: Any) {
        if flashcard != nil{
            self.tailsButton.titleLabel!.text = flashcard!.tails
        }
        presenter.revealCard()
    }
    @IBAction func knowButtonClicked(_ sender: Any) {
        presenter.pushState(state: FlashCardState.ok)
    }
    
    @IBAction func dontKnowButtonClicked(_ sender: Any) {
        presenter.pushState(state: FlashCardState.wrong)
    }
    
    @IBAction func skippedButtonClicked(_ sender: Any) {
        presenter.pushState(state: FlashCardState.skipped)
    }
    
    var lesson: Lesson? = nil
    
    @IBOutlet weak var lessonTitleLabel: UINavigationItem!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let lesson = lesson {
            lessonTitleLabel.title = lesson.name
            
            
            presenter.attachView(view: self)
            presenter.startWithLesson(lesson_id: lesson.id)
        }
    }
    
    func render(state_ : FlashcardViewState) {
        let state = state_
        if state is FlashcardViewState.BEFORE_REVEAL{
            let before_reveal = state as! FlashcardViewState.BEFORE_REVEAL
            displayNewFlashCard(before_reveal.flashcard)
            hideButtons()
        } else if state is FlashcardViewState.AFTER_REVEAL{
            showButtons()
        } else if state is FlashcardViewState.REACHED_END{
            showCurrentSummary(state as! FlashcardViewState.REACHED_END)
        } else if state is FlashcardViewState.FINISHED_LESSON{
            showFinishSummary( (state as! FlashcardViewState.FINISHED_LESSON).rounds)
        }
    }
    
    func displayNewFlashCard(_ flashcard: Flashcard){
        self.flashcard = flashcard
        self.headsButton.titleLabel?.text = self.flashcard?.heads
        self.tailsButton.titleLabel?.text = ""
        
    }
    
    func hideButtons(){
        knowButton.isHidden = true
        dontKnowButton.isHidden = true
        skipButton.isHidden = true
    }
    func showButtons(){
        knowButton.isHidden = false
        dontKnowButton.isHidden = false
        skipButton.isHidden = false
    }
    
    func showCurrentSummary(_ state: FlashcardViewState.REACHED_END){
        let wrongs = state.wrong
        let skipped = state.skipped
        let learned = state.learned
        let size = state.size
        
        Dialogs.showTwoOptionsDialog(target: self,
                                     title: "Your progress",
                                     message: "You answered \(learned.count) of \(size)," +
            " \(wrongs.count) wrong and \(skipped.count) skipped. Do you wish to continue?",
            positiveAction: UIAlertAction(title: "continue", style: UIAlertAction.Style.default, handler: { alertAction in
                self.presenter.generateNewRound()
            }),
            negativeAction: UIAlertAction(title: "exit", style: UIAlertAction.Style.default, handler: { alertAction in
                self.exitScreen()
            }))
    }
    
    func showFinishSummary(_ rounds: Int32){
        Dialogs.showSingleOptionDialog(target: self,
                                       title: "Great job!",
                                       message: "You answered all flashcards in \(rounds) rounds!!!",
            positiveAction: UIAlertAction(title: "back", style: UIAlertAction.Style.default, handler: { alertAction in
            self.exitScreen()
        }))
    }
    
    func exitScreen(){
        self.navigationController?.popViewController(animated: true)
    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
