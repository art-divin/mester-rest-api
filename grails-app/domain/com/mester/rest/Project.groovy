package com.mester.rest

import com.mester.rest.testing.*

class Project {

  String name
  Date dateCreated

  static hasMany = [tests: TestCase, steppedTests: Test]
  static constraints = {
    name nullable: false, blank: false
    tests nullable: true, blank: true
    steppedTests nullable: true, blank: true
  }
}
