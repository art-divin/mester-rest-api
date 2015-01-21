package com.mester.rest.testing

import com.mester.rest.TestCase
import grails.transaction.*

class CaseTest {

  Date dateCreated
  String status

  void setStatus(String status) {
    this.status = status
  }

  String getStatus() {
    this.status
  }

  TestStatus getTestStatus() {
    status ? TestStatus.byStatus(status) : null
  }
  void setTestStatus(TestStatus status) {
    this.status = status.status
  }

  static hasOne = [testCase: TestCase]
  static belongsTo = [test: Test]
  static hasMany = [stepTests: StepTest]
  static mapping = { status sqlType: 'char(7)' }
  static constraints = {
    status nullable: false, blank: false, inList: TestStatus.values()*.status
    test nullable: false, blank: false
    stepTests nullable: true, blank: true
    testCase nullable: false, blank: false
  }

  @Transactional
  def populate() {
    this.testCase.steps.each { it ->
      StepTest stepTest = new StepTest(caseTest: this, step: it, testStatus: TestStatus.DEFAULT)
      if (stepTest.validate()) {
        stepTest.save()
      } else {
        // TODO: handle errors
        println stepTest.errors
      }
    }
  }
}
