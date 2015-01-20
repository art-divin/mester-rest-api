package com.mester.rest.testing

enum TestStatus {
  DEFAULT("default"),
  PASSED("passed"),
  FAILED("failed");

  private String status;

  private TestStatus(String status) {
    this.status = status;
  }
  
  static TestStatus byStatus(String status) {
    values().find { it.status == status }
  }
}
