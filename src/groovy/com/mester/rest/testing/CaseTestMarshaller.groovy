package com.mester.rest.testing

import grails.converters.JSON

class CaseTestMarshaller {
  void register() {
    JSON.registerObjectMarshaller(CaseTest) { CaseTest caseTest ->
      def returnArray = [:]
      returnArray['id'] = caseTest.id as String
      returnArray['status'] = caseTest.status
      returnArray['creationDate'] = caseTest.dateCreated.format('yyyy-MM-dd HH:mm:ss Z')
      returnArray['stepTests'] = caseTest.stepTests?.sort({ StepTest step1, step2 ->
        step1.id == step2.id ? 0 : step1.id > step2.id ? 1 : -1
      })
      returnArray['testId'] = caseTest.test.id as String
      returnArray['testCaseId'] = caseTest.testCase.id as String
      return returnArray
    }
  }
}
