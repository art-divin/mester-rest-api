package com.mester.rest

class TestCaseStep {
  Number number
  String text
  Date dateCreated
  static belongsTo = [testCase: TestCase]
  static constraints = {
    testCase nullable: false, blank: false
    number nullable: false, blank: false // TODO: add min constraint 
    text nullable: false, blank: false
  }
}
