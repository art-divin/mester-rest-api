package com.mester.rest

import grails.rest.*
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.JSON

import javax.servlet.http.*

import com.mester.rest.Project;
import com.mester.rest.TestCase;

@Transactional(readOnly = false)
class TestCaseController extends RestfulController {
  static responseFormat = ['json']
  TestCaseController() {
    super(TestCase)
  }
  def index() {

  }
  def show(TestCase testCase) {
    def responseData = [
      'result': testCase,
      'status': testCase ? "OK" : "Nothing present"
    ]
    render responseData as JSON
  }
  def save() {
    def objectMap = request.JSON
    def responseData = [:]
    Number projectId = objectMap['projectId']
    if (projectId != null) {
      Project project = Project.get(projectId)
      TestCase testCase = new TestCase(title: objectMap['title'], project: project)
      if (testCase.validate()) {
        testCase.save()
        response.sendError(HttpServletResponse.SC_CREATED)
      } else {
        responseData['errors'] = testCase.errors
        println testCase.errors
        response.status = HttpServletResponse.SC_BAD_REQUEST
      }
    } else {
      response.status = HttpServletResponse.SC_BAD_REQUEST
    }
    render responseData as JSON
  }
}
