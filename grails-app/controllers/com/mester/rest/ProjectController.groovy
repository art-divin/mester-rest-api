package com.mester.rest

import com.mester.rest.Project;

import grails.rest.*
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.JSON

import javax.servlet.http.*

import org.apache.tools.ant.taskdefs.Javadoc;
import org.springframework.web.HttpRequestHandler;

@Transactional(readOnly = false)
class ProjectController extends RestfulController {
  static responseFormat = ['json']

  ProjectController() {
    super(Project)
  }

  def index() { }

  def show(Project project) {
    def responseData = [
      'result' : project,
      'status' : project ? "ok" : "error"
    ]
    render responseData as JSON
  }

  def delete(Project project) {
    def responseData = [:]
    try {
      project?.delete(flush: true)
      responseData["status"] = "ok"
    } catch (Exception e) {
      response.status = HttpServletResponse.SC_ACCEPTED
      responseData['errors'] = message(code: "project.not.deleted.message", project?.id)
      responseData["status"] = "error"
    }
    render responseData as JSON
  }

  def listTestCases(Project project) {
    def responseData = [
      'result' : project.tests.sort({ TestCase test1, test2 ->
        test1.dateCreated.compareTo(test2.dateCreated)
      }),
      'status' : project ? "ok" : "error"
    ]
    render responseData as JSON
  }

  def listProjects() {
    def projectList = Project.getAll()
    def responseData = [
      'result' : projectList,
      'status' : projectList ? "ok" : "error"
    ]
    render responseData as JSON
  }

  def save() {
    def objectMap = request.JSON
    def responseData = [:]
    Project project = new Project(objectMap)
    if (project.validate()) {
      project.save()
      response.status = HttpServletResponse.SC_CREATED
      responseData["status"] = "ok"
    } else {
      responseData['errors'] = project.errors
      println project.errors
      response.status = HttpServletResponse.SC_BAD_REQUEST
      responseData["status"] = "error"
    }
    render responseData as JSON
  }
}
