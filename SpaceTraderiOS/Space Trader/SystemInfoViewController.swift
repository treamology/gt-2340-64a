//
//  SystemInfoViewController.swift
//  Space Trader
//
//  Created by Donny Lawrence on 3/3/19.
//  Copyright © 2019 Donny Lawrence. All rights reserved.
//

import UIKit
import QuartzCore

class InformationTableViewController: UITableViewController {
  
}

class SystemInfoViewController: UIViewController {

  @IBOutlet var planetImageView: UIImageView!
  @IBOutlet var planetNameLabel: UILabel!
  
  override func viewDidLoad() {
    super.viewDidLoad()

    planetImageView.layer.magnificationFilter = CALayerContentsFilter.nearest
    planetImageView.layer.shouldRasterize = true
    
  }
  
  override func viewDidAppear(_ animated: Bool) {
    self.parent?.navigationItem.title = "System Info"
  }
}
