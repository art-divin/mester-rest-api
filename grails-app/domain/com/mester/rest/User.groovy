package com.mester.rest

class User {
  String name
  String password
  String email
  static constraints = {
    name nullable: false, blank: false
    email email: true, nullable: false, blank: false, unique: true
    password nullable: false, blank: false
  }
}
