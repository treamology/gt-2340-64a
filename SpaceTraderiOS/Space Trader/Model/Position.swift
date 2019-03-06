//
//  Position.swift
//  Space Trader
//
//  Created by Donny Lawrence on 3/3/19.
//  Copyright © 2019 Donny Lawrence. All rights reserved.
//

import Foundation

struct Position: Hashable {
  let x: UInt
  let y: UInt
  
  init() {
    x = UInt.random(in: 0...SolarSystem.MAX_X)
    y = UInt.random(in: 0...SolarSystem.MAX_Y)
  }
}
