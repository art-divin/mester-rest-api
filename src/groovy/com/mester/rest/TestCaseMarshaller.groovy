package com.mester.rest

import com.mester.rest.TestCase;
import com.mester.rest.TestCaseStep;

import grails.converters.JSON

class TestCaseMarshaller {
  void register() {
    JSON.registerObjectMarshaller(TestCase) { TestCase testCase ->
      def returnArray = [:]
      returnArray['id'] = testCase.id
      returnArray['title'] = testCase.title
      returnArray['creationDate'] = testCase.dateCreated.format('yyyy-MM-dd HH:mm:ss Z')
      returnArray['steps'] = testCase.steps.sort({ TestCaseStep step1, step2 -> 
        step1.number == step2.number ? 0 : step1.number > step2.number ? 1 : -1 
      })
      return returnArray
    }
  }
}
