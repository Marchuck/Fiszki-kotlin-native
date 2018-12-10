//
//  Dialogs.swift
//  KotlinIOS
//
//  Created by Łukasz Marczak on 10.12.2018.
//  Copyright © 2018 Evgeny Petrenko. All rights reserved.
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

}
