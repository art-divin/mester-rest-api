package com.mester.rest

import com.mester.rest.TestCaseStep;

import grails.converters.JSON

class TestCaseStepMarshaller {
  void register() {
    JSON.registerObjectMarshaller(TestCaseStep) { TestCaseStep step ->
      def returnArray = [:]
      returnArray['id'] = step.id
      returnArray['number'] = step.number
      returnArray['text'] = step.text
      return returnArray
    }
  }
}
