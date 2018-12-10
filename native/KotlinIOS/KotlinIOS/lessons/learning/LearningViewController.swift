//
//  LearningViewController.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 10.12.2018.
//  Copyright © 2018 Evgeny Petrenko. All rights reserved.
//

import UIKit

import SharedCode

class LearningViewController: UIViewController {
    
    var lesson: Lesson? = nil
    
    @IBOutlet weak var learningLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        if let lesson = lesson {
            learningLabel.text = lesson.name

        }
        // Do any additional setup after loading the view.
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
