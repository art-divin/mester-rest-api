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
      returnArray['caseTests'] = test.caseTests?.sort({ CaseTest test1, test2 ->
        test1.id == test2.id ? 0 : test1.id > test2.id ? 1 : -1
      })
      return returnArray
    }
  }
}
