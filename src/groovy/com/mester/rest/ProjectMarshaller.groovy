package com.mester.rest

import com.mester.rest.Project;

import grails.converters.JSON

class ProjectMarshaller {
  void register() {
    JSON.registerObjectMarshaller(Project) { Project project ->
      def returnArray = [:]
      returnArray['id'] = project.id as String
      returnArray['name'] = project.name
      returnArray['creationDate'] = project.dateCreated.format('yyyy-MM-dd HH:mm:ss Z')
      return returnArray
    }
  }
}
