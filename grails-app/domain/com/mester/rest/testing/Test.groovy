package com.mester.rest.testing

import com.mester.rest.Project;

import grails.transaction.Transactional;

import java.util.Date;

class Test {

  Date dateCreated
  Date dateStarted
  Date dateEnded
  private String status

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

  static transients = ['status']

  static belongsTo = [project: Project]
  static hasMany = [caseTests: CaseTest]
  static mapping = { status sqlType: 'char(7)' }
  static constraints = {
    dateStarted nullable: true, blank: true
    dateEnded nullable: true, blank: true
    project nullable: false, blank: false
    caseTests nullable: true, blank: true
    status nullable: false, blank: false, inList: TestStatus.values()*.status
  }

  @Transactional
  def populate() {
    this.project.tests.each { it ->
      CaseTest test = new CaseTest(test: this, testCase: it, testStatus: TestStatus.DEFAULT)
      if (test.validate()) {
        test.save()
      } else {
        // TODO: handle errors
        println test.errors
      }
    }
  }
  
  def populateSteps() {
    this.caseTests.each { it ->
      it.populate()
    }
  }
}
