package com.mester.rest

class TestCaseStep {
  Number number
  String text
  static belongsTo = [testCase: TestCase]
  static constraints = {
    testCase nullable: false, blank: false
    number nullable: false, blank: false
    text nullable: false, blank: false
  }
}
