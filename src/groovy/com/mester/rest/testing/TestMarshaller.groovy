package com.mester.rest.testing

import grails.converters.JSON

class TestMarshaller {
  void register() {
    JSON.registerObjectMarshaller(Test) { Test test ->
      def returnArray = [:]
      returnArray['id'] = test.id as String
      returnArray['status'] = test.status
      returnArray['creationDate'] = test.dateCreated.format('yyyy-MM-dd HH:mm:ss Z')
      returnArray['startDate'] = test.dateStarted?.format('yyyy-MM-dd HH:mm:ss Z')
      returnArray['endDate'] = test.dateEnded?.format('yyyy-MM-dd HH:mm:ss Z')
      returnArray['projectId'] = test.project.id as String
      returnArray['caseTests'] = test.caseTests
      return returnArray
    }
  }
}
