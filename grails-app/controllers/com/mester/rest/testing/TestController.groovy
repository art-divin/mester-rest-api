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

  def saveTest() {
    def testId = params.id
    def requestData = request.JSON
    def responseData = [ 'status' : "error" ]
    if (testId != null) {
      Test test = Test.get(testId)
      if (test != null) {
        if (test.dateEnded != null || test.status == TestStatus.PASSED) {
          response.status = HttpServletResponse.SC_CONFLICT
          render responseData as JSON
          return
        }
        if (test.dateStarted == null) {
          response.status = HttpServletResponse.SC_BAD_REQUEST
          render responseData as JSON
          return
        }
        def caseTests = requestData['caseTests']
        if (caseTests == null) {
          response.status = HttpServletResponse.SC_BAD_REQUEST
          render responseData as JSON
          return
        }
        caseTests.each { caseTestMap ->
          def caseTestId = caseTestMap['id']
          CaseTest caseTest = CaseTest.get(caseTestId)
          if (caseTest == null) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
          }
          if (caseTestId == null) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
          }
          def stepTests = caseTestMap['stepTests']
          if (stepTests == null) {
            response.status = HttpServletResponse.SC_BAD_REQUEST
          }
          stepTests.each { map ->
            def stepTestId = map['id']
            def stepTestStatus = map['status']
            if (stepTestId == null || stepTestStatus == null) {
              response.status = HttpServletResponse.SC_BAD_REQUEST
            }
            StepTest stepTest = StepTest.get(stepTestId)
            if (stepTest == null) {
              response.status = HttpServletResponse.SC_NOT_FOUND
            }
            if (!caseTest.stepTests.find { it == stepTest }) {
              response.status = HttpServletResponse.SC_BAD_REQUEST
            }
            stepTest.testStatus = TestStatus.byStatus(stepTestStatus)
            if (stepTest.testStatus == TestStatus.FAILED) {
              caseTest.testStatus = TestStatus.FAILED
            }
            stepTest.save(flush: true)
          }
          def unfinishedStepTests = caseTest.stepTests.findAll { stepTest ->
            stepTest.testStatus == TestStatus.DEFAULT
          }
          if (unfinishedStepTests.size() != 0) {
            caseTest.testStatus = TestStatus.DEFAULT
          } else {
            if (caseTest.testStatus != TestStatus.FAILED) {
              caseTest.testStatus = TestStatus.PASSED
            }
          }
          caseTest.save(flush: true)
        }
        test.refresh()
        if (response.status != HttpServletResponse.SC_OK) {
          render responseData as JSON
          return
        }
        def failedCaseTests = test.caseTests.findAll { caseTest ->
          caseTest.testStatus == TestStatus.FAILED
        }
        def finishedCaseTests = test.caseTests.findAll { caseTest ->
          caseTest.testStatus == TestStatus.PASSED
        }
        
        if (failedCaseTests?.size() > 0) {
          if (failedCaseTests?.size() + finishedCaseTests?.size() == test.caseTests.size()) {
            test.testStatus = TestStatus.FAILED
            test.dateEnded = new Date()
          }
        } else if (finishedCaseTests?.size() == test.caseTests.size()) {
          test.testStatus = TestStatus.PASSED
          test.dateEnded = new Date()
        }
        test.save(flush: true)
        test.refresh()
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
