//
//  ViewController.swift
//  Space Trader
//
//  Created by Donny Lawrence on 2/19/19.
//  Copyright © 2019 Donny Lawrence. All rights reserved.
//

import UIKit

class ConfigurationViewController: UITableViewController, SkillPointsDelegate, UIPickerViewDelegate, UIPickerViewDataSource {

  @IBOutlet var pilotCell: SkillPointsTableViewCell!
  @IBOutlet var fighterCell: SkillPointsTableViewCell!
  @IBOutlet var traderCell: SkillPointsTableViewCell!
  @IBOutlet var engineerCell: SkillPointsTableViewCell!
  @IBOutlet var difficultySelectorTextField: UITextField!
  
  var difficultyPicker: UIPickerView!
  
  
  let maxSkillPoints: Int = 16;
  var totalSkillPoints: Int {
    get {
      return pilotCell.getNumPoints() + fighterCell.getNumPoints() + traderCell.getNumPoints() + engineerCell.getNumPoints();
    }
  }
  
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view, typically from a nib.
    
    pilotCell?.pointsType = "Pilot"
    fighterCell?.pointsType = "Fighter"
    traderCell?.pointsType = "Trader"
    engineerCell?.pointsType = "Engineer"
    
    let doneToolbar = UIToolbar()
    doneToolbar.barStyle = .default
    doneToolbar.isTranslucent = true
    doneToolbar.sizeToFit()
    doneToolbar.setItems([UIBarButtonItem(title: "Done", style: .done, target: difficultySelectorTextField, action: #selector(difficultySelectorTextField!.resignFirstResponder))], animated: false)
    
    
    difficultyPicker = UIPickerView()
    difficultyPicker.dataSource = self
    difficultyPicker.delegate = self
    difficultySelectorTextField.inputView = difficultyPicker
    difficultySelectorTextField.inputAccessoryView = doneToolbar
  }
  
  func skillPointsDidChange(toValue value: Int) {
    tableView.headerView(forSection: 1)?.textLabel?.text = String(format: "SKILL POINTS - %d REMAINING", maxSkillPoints - totalSkillPoints)
  }
  
  func numberOfComponents(in pickerView: UIPickerView) -> Int {
    return 1
  }
  
  func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
    return GameState.Difficulty.allCases.count
  }
  
  func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
    return GameState.Difficulty.allCases[row].rawValue
  }
  
  func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
    difficultySelectorTextField.text = GameState.Difficulty.allCases[row].rawValue
  }
}

