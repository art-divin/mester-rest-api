package com.mester.rest.testing

import grails.rest.*
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*
import grails.converters.JSON

import javax.servlet.http.*

import org.springframework.web.HttpRequestHandler;

@Transactional(readOnly = false)
class TestController extends RestfulController {
  static responseFormat = ['json']
  def index() { }

  /*
   * "/test/$id/start" {
   controller = 'Test'
   action = [POST: 'startTest']
   }
   "/test/$id/end" {
   controller = 'Test'
   action = [UPDATE: 'endTest']
   }
   "/test/$id" {
   controller = 'Test'
   action = [GET: 'show']
   }
   "/project/$id/tests" {
   controller = 'Test'
   action = [GET: 'list']
   }
   "/project/$id/test" {
   controller = 'Test'
   action = [POST: 'save']
   }
   * */

  def startTest() {
    def testId = params.id
  }

  def endTest() {
  }

  def show() {
  }

  def list() {
  }
}
