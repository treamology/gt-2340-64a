//
//  ViewController.swift
//  Space Trader
//
//  Created by Donny Lawrence on 2/19/19.
//  Copyright © 2019 Donny Lawrence. All rights reserved.
//

import UIKit

class ConfigurationViewController: UITableViewController {

  @IBOutlet var pilotCell: SkillPointsTableViewCell?
  @IBOutlet var fighterCell: SkillPointsTableViewCell?
  @IBOutlet var traderCell: SkillPointsTableViewCell?
  @IBOutlet var engineerCell: SkillPointsTableViewCell?
  
  override func viewDidLoad() {
    super.viewDidLoad()
    // Do any additional setup after loading the view, typically from a nib.
  }
  
  override func loadView() {
    super.loadView()
    
    pilotCell?.pointsType = "Pilot"
    fighterCell?.pointsType = "Fighter"
    traderCell?.pointsType = "Trader"
    engineerCell?.pointsType = "Engineer"
  }


}

