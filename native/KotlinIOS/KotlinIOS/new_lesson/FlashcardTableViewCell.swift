//
//  FlashcardTableViewCell.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 10.12.2018.
//

import UIKit

import SharedCode
typealias Listener = (_ flashcard: Flashcard) -> Void

class FlashcardTableViewCell: UITableViewCell {
    
    var listener: Listener? = nil
    var flashcard: Flashcard? = nil

    @IBOutlet weak var translationFrom: UILabel!
    
    @IBOutlet weak var translationTo: UILabel!
    
    @IBAction func removeFlashcardClicked(_ sender: Any) {
        if listener != nil {
            listener!(flashcard!)
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        // Configure the view for the selected state
    }
    
    func bind(flashcard: Flashcard, listener: @escaping Listener){
        self.translationFrom.text = flashcard.heads
        self.translationTo.text = flashcard.tails
        self.listener = listener
    }

}
