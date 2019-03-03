//
//  SkillPointsController.swift
//  Space Trader
//
//  Created by Donny Lawrence on 3/2/19.
//  Copyright © 2019 Donny Lawrence. All rights reserved.
//

import Foundation

protocol SkillPointsDelegate {
  var maxSkillPoints: Int { get }
  var totalSkillPoints: Int { get }
}
