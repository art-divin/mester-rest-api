package com.mester.rest

import com.mester.rest.TestCaseStep;

import grails.converters.JSON

class TestCaseStepMarshaller {
  void register() {
    JSON.registerObjectMarshaller(TestCaseStep) { TestCaseStep step ->
      def returnArray = [:]
      returnArray['id'] = step.id as String
      returnArray['number'] = step.number
      returnArray['text'] = step.text
      returnArray['creationDate'] = step.dateCreated.format('yyyy-MM-dd HH:mm:ss Z')
      returnArray['testCaseId'] = step.testCase.id as String
      return returnArray
    }
  }
}
