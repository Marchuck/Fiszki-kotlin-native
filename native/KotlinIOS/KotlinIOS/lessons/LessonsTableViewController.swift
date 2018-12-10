//
//  LessonsTableViewController.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 29.11.2018.
//  Copyright © 2018 Evgeny Petrenko. All rights reserved.
//

import UIKit

import SharedCode

class LessonsTableViewController: UITableViewController, LessonsView{
    
    var recentLessonClicked: Lesson? = nil
  
    var lessons : [Lesson] = []
    
    func render(state: LessonsViewState) {
        if state is LessonsViewState.Content {
            let content: LessonsViewState.Content = state as! LessonsViewState.Content
            
            self.lessons = content.lessons
                
            self.tableView.reloadData()
        } else if state is LessonsViewState.EnterLesson {
            let enter: LessonsViewState.EnterLesson = state as! LessonsViewState.EnterLesson
            let lessonClicked = enter.lesson
            recentLessonClicked = lessonClicked
            self.performSegue(withIdentifier: "learningViewController", sender: self)
            
        } else if state is LessonsViewState.Loading {
            
        } else if state is LessonsViewState.Error {
            let error: LessonsViewState.Error = state as! LessonsViewState.Error
            let message = error.message
            
            Dialogs.showWarningDialog(target: self, message: message)
        }
    }
    
    let repo: FlashcardsRepository = IOSFlashcardsRepository.INSTANCE
    
    let presenter = LessonsPresenter(lessonsUseCase: LessonsUseCase(flashcardsRepository: IOSFlashcardsRepository.INSTANCE))

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.tableView.rowHeight = UITableView.automaticDimension
        self.tableView.estimatedRowHeight = 90.0
        
        presenter.attachView(view: self)
        presenter.requestLessons()
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return lessons.count
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "lessonCell", for: indexPath) as UITableViewCell
        
        if cell is LessonTableViewCell{
            let lessonCell = cell as! LessonTableViewCell
            
            let lesson = lessons[indexPath.row]
            
            lessonCell.bind(lesson: lesson)
        }
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let lesson = lessons[indexPath.row]
        print("clicked lesson \(lesson.name)")
        presenter.onLessonClick(lesson: lesson)
    }
    
    /*
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)

        // Configure the cell...

        return cell
    }
    */

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        switch segue.identifier {
        case "learningViewController":
            let learningScreen = segue.destination as! LearningViewController
            if recentLessonClicked != nil{
                learningScreen.lesson = recentLessonClicked
            }
        default: break
        }
    }

}
