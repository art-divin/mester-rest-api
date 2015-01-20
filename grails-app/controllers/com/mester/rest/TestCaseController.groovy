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
    // TODO: null checking
    def responseData = [
      'result': testCase,
      'status': testCase ? "ok" : "error"
    ]
    render responseData as JSON
  }

  def delete(TestCase testCase) {
    def responseData = [:]
    try {
      testCase?.delete(flush: true)
      responseData["status"] = "ok"
      response.status = HttpServletResponse.SC_ACCEPTED
    } catch (Exception e) {
      response.status = HttpServletResponse.SC_NOT_FOUND
      responseData['errors'] = message(code: "testcase.not.deleted.message", testCase?.id)
      responseData["status"] = "error"
    }
    render responseData as JSON
  }

  def saveStep() {
    def objectMap = request.JSON
    def responseData = [:]
    String testCaseId = objectMap['testCaseId']
    if (testCaseId != null) {
      def testCase = TestCase.get(testCaseId)
      TestStep step = new TestStep(text: objectMap['text'], number: objectMap['number'], testCase: testCase)
      if (step.validate()) {
        step.save()
        response.status = HttpServletResponse.SC_CREATED
        responseData["status"] = "ok"
      } else {
        responseData['errors'] = step.errors
        println step.errors
        response.status = HttpServletResponse.SC_BAD_REQUEST
        responseData["status"] = "error"
      }
    } else {
      response.status = HttpServletResponse.SC_BAD_REQUEST
      responseData["status"] = "error"
    }
    render responseData as JSON
  }

  // TODO: null checking
  def showTestSteps(TestCase testCase) {
    def responseData = [
      'result': testCase.steps,
      'status': testCase ? "ok" : "error"
    ]
    render responseData as JSON
  }

  def deleteStep() {
    // TODO: better error handling for case when object was already removed
    def stepId = params.id
    def step = TestStep.get(stepId)
    def responseData = [:]
    try {
      step?.delete(flush: true)
      responseData["status"] = "ok"
      response.status = HttpServletResponse.SC_ACCEPTED
    } catch (Exception e) {
      response.status = HttpServletResponse.SC_NOT_FOUND
      responseData['errors'] = message(code: "testcase.not.deleted.message", step?.id)
      responseData["status"] = "error"
    }
    render responseData as JSON
  }

  def save() {
    def objectMap = request.JSON
    def responseData = [:]
    String projectId = objectMap['projectId']
    if (projectId != null) {
      Project project = Project.get(projectId)
      TestCase testCase = new TestCase(title: objectMap['title'], project: project)
      if (testCase.validate()) {
        testCase.save()
        response.status = HttpServletResponse.SC_CREATED
        responseData["status"] = "ok"
      } else {
        responseData['errors'] = testCase.errors
        println testCase.errors
        response.status = HttpServletResponse.SC_BAD_REQUEST
        responseData["status"] = "error"
      }
    } else {
      response.status = HttpServletResponse.SC_BAD_REQUEST
      responseData["status"] = "error"
    }
    render responseData as JSON
  }
}
