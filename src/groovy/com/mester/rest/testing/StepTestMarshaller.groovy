package com.mester.rest.testing

import grails.converters.JSON

class StepTestMarshaller {
  void register() {
    JSON.registerObjectMarshaller(StepTest) { StepTest stepTest ->
      def returnArray = [:]
      returnArray['id'] = stepTest.id as String
      returnArray['status'] = stepTest.status
      returnArray['creationDate'] = stepTest.dateCreated.format('yyyy-MM-dd HH:mm:ss Z')
      returnArray['testStepId'] = stepTest.step.id as String
      returnArray['caseTestId'] = stepTest.caseTest.id as String
      return returnArray
    }
  }
}
