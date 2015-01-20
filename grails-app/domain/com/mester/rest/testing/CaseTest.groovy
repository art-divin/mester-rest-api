package com.mester.rest.testing

class CaseTest {

  Date dateCreated
  private String status

  TestStatus getStatus() {
    status ? TestStatus.byStatus(status) : null
  }
  void setStatus(TestStatus status) {
    this.status = status.status
  }
  
  static hasOne = [test: Test]
  static hasMany = [stepTests: StepTest]
  static mapping = { status sqlType: 'char(7)' }
  static constraints = {
    status nullable: false, blank: false, inList: TestStatus.values()*.status
    test nullable: false, blank: false
    stepTests nullable: false, blank: false
  }
}
