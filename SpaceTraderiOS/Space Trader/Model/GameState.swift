//
//  GameState.swift
//  Space Trader
//
//  Created by Donny Lawrence on 3/2/19.
//  Copyright © 2019 Donny Lawrence. All rights reserved.
//

import Foundation

class GameState {
  enum Difficulty: String, CaseIterable {
    case beginner = "Beginner"
    case easy = "Easy"
    case normal = "Normal"
    case hard = "Hard"
    case impossible = "Impossible"
  }
  static let MAX_POINTS: Int = 16
  
  let currentDifficulty: Difficulty
  let universe: Universe
  let player: Player
  
  init(difficulty: Difficulty) {
    currentDifficulty = difficulty
    
    universe = Universe()
    universe.generateUniverse()
    
    player = Player(currentSystem: universe.solarSystems[0])
  }
}
