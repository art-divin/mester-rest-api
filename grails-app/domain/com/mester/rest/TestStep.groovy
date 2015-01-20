package com.mester.rest

import com.mester.rest.testing.StepTest;

class TestStep {
  
  Number number
  String text
  Date dateCreated
  
  static belongsTo = [testCase: TestCase]
  static hasMany = [steppedTests: StepTest]
  static constraints = {
    testCase nullable: false, blank: false
    number nullable: false, blank: false // TODO: add min constraint 
    text nullable: false, blank: false
    steppedTests nullable: true, blank: true
  }
}
