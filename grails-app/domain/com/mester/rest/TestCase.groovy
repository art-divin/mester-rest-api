package com.mester.rest

class TestCase {
  Date dateCreated
  String title
  static hasMany = [steps: TestCaseStep]
  static belongsTo = [project: Project]
  static constraints = {
    title nullable: false, blank: false
    project nullable: false
  }
}
