package com.mester.rest

class Project {
  static hasMany = [tests: TestCase]
  String name
  Date dateCreated
  static constraints = {
    name nullable: false, blank: false
  }
}
