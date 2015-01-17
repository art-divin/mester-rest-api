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
      'status' : project ? "OK" : "Nothing present"
    ]
    render responseData as JSON
  }

  def listTestCases(Project project) {
    def responseData = [
      'results' : project.tests,
      'status' : project ? "OK" : "Nothing present"
    ]
    render responseData as JSON
  }

  def listProjects() {
    def projectList = Project.getAll()
    def responseData = [
      'results' : projectList,
      'status' : projectList ? "OK" : "Nothing present"
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
    } else {
      responseData['errors'] = project.errors
      println project.errors
      response.status = HttpServletResponse.SC_BAD_REQUEST
    }
    render responseData as JSON
  }
}
