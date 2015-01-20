package com.mester.rest

import com.mester.rest.TestCase;
import com.mester.rest.TestStep;

import grails.converters.JSON

class TestCaseMarshaller {
  void register() {
    JSON.registerObjectMarshaller(TestCase) { TestCase testCase ->
      def returnArray = [:]
      returnArray['id'] = testCase.id as String
      returnArray['title'] = testCase.title
      returnArray['creationDate'] = testCase.dateCreated.format('yyyy-MM-dd HH:mm:ss Z')
      returnArray['projectId'] = testCase.project.id as String
      returnArray['steps'] = testCase.steps.sort({ TestStep step1, step2 -> 
        step1.number == step2.number ? 0 : step1.number > step2.number ? 1 : -1 
      })
      return returnArray
    }
  }
}
