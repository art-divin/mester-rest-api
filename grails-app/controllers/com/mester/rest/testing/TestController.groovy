package com.mester.rest.testing

import grails.rest.*

import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.JSON

import javax.servlet.http.*

import org.springframework.web.HttpRequestHandler;

import com.mester.rest.Project

@Transactional(readOnly = false)
class TestController extends RestfulController {
  static responseFormat = ['json']

  def index() { }

  def startTest() {
    def testId = params.id
    def responseData = [ 'status' : "error" ]
    if (testId != null) {
      Test test = Test.get(testId)
      if (test != null) {
        if (test.dateStarted == null) {
          test.dateStarted = new Date()
          responseData['result'] = test
          responseData['status'] = 'ok'
        } else {
          response.status = HttpServletResponse.SC_CONFLICT
        }
      } else {
        response.status = HttpServletResponse.SC_NOT_FOUND
      }
    } else {
      response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }
    render responseData as JSON
  }

  def endTest() {
    def testId = params.id
    def requestData = request.JSON
    def responseData = [ 'status' : "error" ]
    if (testId != null) {
      Test test = Test.get(testId)
      if (test != null) {
        if (test.dateEnded != null || test.status == TestStatus.PASSED) {
          response.sendError(HttpServletResponse.SC_CONFLICT)
        }
        if (test.dateStarted == null) {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST)
        }
        def caseTests = requestData['caseTests']
        if (caseTests == null) {
          // TODO: return on bad requests
          response.sendError(HttpServletResponse.SC_BAD_REQUEST)
        }
        caseTests.each { caseTestMap ->
          def caseTestId = caseTestMap['id']
          CaseTest caseTest = CaseTest.get(caseTestId)
          if (caseTest == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST)
          }
          if (caseTestId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST)
          }
          def stepTests = caseTestMap['stepTests']
          if (stepTests == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST)
          }
          stepTests.each { map ->
            def stepTestId = map['id']
            def stepTestStatus = map['status']
            if (stepTestId == null || stepTestStatus == null) {
              response.sendError(HttpServletResponse.SC_BAD_REQUEST)
            }
            StepTest stepTest = StepTest.get(stepTestId)
            if (stepTest == null) {
              response.sendError(HttpServletResponse.SC_NOT_FOUND)
            }
            if (!caseTest.stepTests.find { it == stepTest }) {
              response.sendError(HttpServletResponse.SC_BAD_REQUEST)
            }
            stepTest.testStatus = TestStatus.byStatus(stepTestStatus)
            if (stepTest.testStatus == TestStatus.FAILED) {
              caseTest.testStatus = TestStatus.FAILED
            }
            stepTest.save()
          }
          switch (caseTest.testStatus) {
            case TestStatus.FAILED:
              test.testStatus = TestStatus.FAILED
              break;
            case TestStatus.DEFAULT:
              caseTest.testStatus = TestStatus.PASSED
              break;
            default:
              break;
          }
          caseTest.save()
        }

        def finishedCaseTests = test.caseTests.findAll { caseTest ->
          caseTest.testStatus == TestStatus.PASSED
        }
        if (finishedCaseTests?.size() == test.caseTests.size()) {
          if (test.testStatus == TestStatus.DEFAULT) {
            test.testStatus = TestStatus.PASSED
          }
          test.dateEnded = new Date()
          test.save()
        }
        responseData['result'] = test
        responseData['status'] = 'ok'
      } else {
        response.status = HttpServletResponse.SC_BAD_REQUEST
      }
    } else {
      response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }
    render responseData as JSON
  }

  def show() {
    def testId = params.id
    def responseData = [ 'status' : "error" ]
    if (testId != null) {
      Test test = Test.get(testId)
      if (test != null) {
        responseData['result'] = test
        responseData['status'] = 'ok'
      } else {
        response.status = HttpServletResponse.SC_NOT_FOUND
      }
    } else {
      response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }
    render responseData as JSON
  }

  def list() {
    def projectId = params.id
    def responseData = [ 'status' : "error" ]
    if (projectId != null) {
      Project project = Project.get(projectId)
      if (project != null) {
        responseData['result'] = project.steppedTests
        responseData['status'] = "ok"
      } else {
        response.status = HttpServletResponse.SC_NOT_FOUND
      }
    } else {
      response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }
    render responseData as JSON
  }

  def save() {
    def projectId = params.id
    def responseData = [ 'status' : 'error' ]
    if (projectId != null) {
      Project project = Project.get(projectId)
      if (project != null) {
        Test test = new Test(project: project, testStatus: TestStatus.DEFAULT)
        if (test.validate()) {
          test.save()
          test.populate()
          test.refresh()
          test.populateSteps()
          responseData['result'] = test
          responseData['status'] = 'ok'
        } else {
          response.status = HttpServletResponse.SC_BAD_REQUEST
        }
      } else {
        response.status = HttpServletResponse.SC_NOT_FOUND
      }
    } else {
      response.status = HttpServletResponse.SC_NOT_ACCEPTABLE
    }
    render responseData as JSON
  }

  def delete() {
    def testId = params.id
    if (testId == null) {
      response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE)
    }
    Test test = Test.get(testId)
    if (test == null) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND)
    }
    test?.delete()
    response.status = HttpServletResponse.SC_OK
    render [:] as JSON
  }
}
