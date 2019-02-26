//
//  SkillPointsTableViewCell.swift
//  Space Trader
//
//  Created by Donny Lawrence on 2/19/19.
//  Copyright © 2019 Donny Lawrence. All rights reserved.
//

import UIKit

@IBDesignable
class SkillPointsTableViewCell: UITableViewCell {
  private var ibDesignableView: SkillPointsTableViewCell?
  
  var pointsType = "" {
    didSet {
      pointsTitleLabel?.text = pointsType
    }
  }
  var pointsCount: Int {
    get {
      return Int(stepper?.value ?? 0)
    }
  }
  
  @IBOutlet var stepper: UIStepper?
  @IBOutlet var pointsTitleLabel: UILabel?
  @IBOutlet var pointsCountLabel: UILabel?
  
  override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
    super.init(style: style, reuseIdentifier: reuseIdentifier)
    let view = self.loadNib()
    view.frame = self.bounds
    view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
    self.ibDesignableView = view
    self.addSubview(view)
  }
  
  required init?(coder aDecoder: NSCoder) {
    super.init(coder: aDecoder)
  }
  
  override func awakeAfter(using aDecoder: NSCoder) -> Any? {
    if self.subviews[0].subviews.count == 0 {
      let view = loadNib()
      view.translatesAutoresizingMaskIntoConstraints = false
      let constraints = self.constraints
      self.removeConstraints(constraints)
      view.addConstraints(constraints)
      return view
    }
    return self
  }
  
  private func loadNib() -> SkillPointsTableViewCell {
    let bundle = Bundle(for: type(of: self))
    let view = bundle.loadNibNamed("SkillPointsTableViewCell", owner: nil, options: nil)![0] as! SkillPointsTableViewCell
    return view
  }
  
  @IBAction func stepperValueChanged(_ sender: UIStepper) {
    pointsCountLabel?.text = String(pointsCount)
  }
}
