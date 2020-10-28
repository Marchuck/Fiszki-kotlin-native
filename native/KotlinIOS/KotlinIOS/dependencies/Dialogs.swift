//
//  Dialogs.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 10.12.2018.
//

import Foundation
import UIKit

class Dialogs {
    
    public static func showWarningDialog(target: UIViewController, message: String){
        // create the alert
        let alert = UIAlertController(title: "Warning", message: message, preferredStyle: UIAlertController.Style.alert)
        
        // add an action (button)
        alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
        
        // show the alert
        target.present(alert, animated: true, completion: nil)
    }
    
    public static func showTwoOptionsDialog(target: UIViewController,
                                            title: String,
                                            message: String,
                                            positiveAction: UIAlertAction,
                                            negativeAction: UIAlertAction){
        // create the alert
        let alert = UIAlertController(title: title, message: message, preferredStyle: UIAlertController.Style.actionSheet)
        
        // add an action (button)
        alert.addAction(positiveAction)
        alert.addAction(negativeAction)

        // show the alert
        target.present(alert, animated: true, completion: nil)
    }
    public static func showSingleOptionDialog(target: UIViewController,
                                             title: String,
                                             message: String,
                                             positiveAction: UIAlertAction){
        // create the alert
        let alert = UIAlertController(title: title, message: message, preferredStyle: UIAlertController.Style.actionSheet)
        
        // add an action (button)
        alert.addAction(positiveAction)
        
        // show the alert
        target.present(alert, animated: true, completion: nil)
    }

}
