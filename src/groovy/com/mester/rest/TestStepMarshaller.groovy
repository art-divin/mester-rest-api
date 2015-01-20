package com.mester.rest

import com.mester.rest.TestStep;

import grails.converters.JSON

class TestStepMarshaller {
  void register() {
    JSON.registerObjectMarshaller(TestStep) { TestStep step ->
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
