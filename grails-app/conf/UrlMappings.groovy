class UrlMappings {

  static mappings = {
    "/"(view:"/index")
    "500"(view:'/error')
    
    // TEST CASE
    "/testcase/$id" {
      controller = "TestCase"
      action = [GET: "show", DELETE: "delete"]
    }
    "/testcase" {
      controller = "TestCase"
      action = [POST: "save"]
    }
    
    // STEP
    "/step" {
      controller = "TestCase"
      action = [POST: "saveStep"]
    }
    "/testcase/$id/steps" {
      controller = 'TestCase'
      action = [GET: 'showTestSteps']
    }
    "/step/$id" {
      controller = "TestCase"
      action = [DELETE: "deleteStep"]
    }
    
    // PROJECT
    "/project/$id/testcases" {
      controller = 'Project'
      action = [GET: "listTestCases"]
    }
    "/project/$id" {
      controller = "Project"
      action = [GET: "show", DELETE: "delete"]
    }
    "/projects" {
      controller = 'Project'
      action = [GET: "listProjects"]
    }
    "/project" {
      controller = 'Project'
      action = [POST: "save"]
    }
    
    // TEST
    "/test/$id/start" {
      controller = 'Test'
      action = [PUT: 'startTest']
    }
    "/test/$id/end" {
      controller = 'Test'
      action = [PUT: 'endTest']
    }
    "/test/$id" {
      controller = 'Test'
      action = [GET: 'show', DELETE: "delete"]
    }
    "/project/$id/tests" {
      controller = 'Test'
      action = [GET: 'list']
    }
    "/project/$id/test" {
      controller = 'Test'
      action = [POST: 'save']
    }
  }
}