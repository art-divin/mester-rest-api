package com.mester.rest

import com.mester.rest.testing.*

class TestCase {

  Date dateCreated
  String title

  static hasMany = [steps: TestStep, caseTests: CaseTest]
  static belongsTo = [project: Project]
  static constraints = {
    title nullable: false, blank: false
    project nullable: false, blank: false
    caseTests nullable: true, blank: true
  }
}
