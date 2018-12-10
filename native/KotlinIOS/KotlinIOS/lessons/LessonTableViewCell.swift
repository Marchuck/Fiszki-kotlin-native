//
//  LessonTableViewCell.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 10.12.2018.
//  Copyright © 2018 Evgeny Petrenko. All rights reserved.
//

import UIKit

import SharedCode

class LessonTableViewCell: UITableViewCell {
    @IBOutlet weak var lessonNameLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func bind(lesson: Lesson){
        lessonNameLabel.text = lesson.name
    }

}
