package com.mester.rest.testing

import com.mester.rest.TestStep;
import org.codehaus.groovy.grails.orm.hibernate.cfg.IdentityEnumType

class StepTest {

  Date dateCreated
  private String status

  TestStatus getStatus() {
    status ? TestStatus.byStatus(status) : null
  }
  void setStatus(TestStatus status) {
    this.status = status.status
  }

  static hasOne = [step: TestStep]
  static mapping = { status sqlType: 'char(7)' }
  static belongsTo = [caseTest: CaseTest]
  static constraints = {
    status nullable: false, blank: false, inList: TestStatus.values()*.status
    caseTest nullable: false, blank: false
    step nullable: false, blank: false
  }
}
