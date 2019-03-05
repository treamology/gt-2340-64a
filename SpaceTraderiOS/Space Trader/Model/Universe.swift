//
//  Universe.swift
//  Space Trader
//
//  Created by Donny Lawrence on 3/3/19.
//  Copyright © 2019 Donny Lawrence. All rights reserved.
//

import UIKit

class Universe {
  static let NUM_SOLAR_SYSTEMS = 10
  
  var usedSystemNames: Set<String> = []
  var usedSystemPositions: Set<Position> = []
  var usedSystemImages: Set<UInt> = []
  var solarSystems: [SolarSystem] = []
  
  func generateUniverse() {
    for i in 0...Universe.NUM_SOLAR_SYSTEMS {
      let system = SolarSystem(position: generateSolarSystemPosition(),
        name: generateSolarSystemName(),
        image: generateSolarSystemImage())
      solarSystems.append(system)
    }
  }
  
  private func generateSolarSystemName() -> String {
    var generatedName: String! = nil
    repeat {
      generatedName = SolarSystem.SYSTEM_NAMES.randomElement()
    } while !usedSystemNames.contains(generatedName)
    usedSystemNames.insert(generatedName)
    return generatedName
  }
  
  private func generateSolarSystemPosition() -> Position {
    var generatedPosition: Position! = nil
    repeat {
      generatedPosition = Position()
    } while !usedSystemPositions.contains(generatedPosition)
    usedSystemPositions.insert(generatedPosition)
    return generatedPosition
  }
  
  private func generateSolarSystemImage() -> UIImage {
    var imageIndex: UInt! = nil
    repeat {
      imageIndex = UInt.random(in: 0...SolarSystem.NUM_IMAGES)
    } while !usedSystemImages.contains(imageIndex)
    usedSystemImages.insert(imageIndex)
    
    let image = UIImage(contentsOfFile: String(format: "Planets/planet%d.png", imageIndex))
    return image!
  }
}
