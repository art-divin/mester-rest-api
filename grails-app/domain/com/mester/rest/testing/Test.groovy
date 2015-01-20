package com.mester.rest.testing

import com.mester.rest.Project;
import java.util.Date;

class Test {

  Date dateCreated
  Date dateStarted
  Date dateEnded
  private String status

  TestStatus getStatus() {
    status ? TestStatus.byStatus(status) : null
  }
  void setStatus(TestStatus status) {
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
    caseTests nullable: false, blank: false
    status nullable: false, blank: false, inList: TestStatus.values()*.status
  }

  def start() {
  }
}
