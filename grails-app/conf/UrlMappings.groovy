class UrlMappings {

  static mappings = {
    "/"(view:"/index")
    "500"(view:'/error')
    "/testcase/$id" {
      controller = "TestCase"
      action = [GET: "show"]
    }
    "/testcase" {
      controller = "TestCase"
      action = [POST: "save"]
    }
    "/project/$id" {
      controller = "Project"
      action = [GET: "show"]
    }
    "/project/$id/testcases" {
      controller = 'Project'
      action = [GET: "listTestCases"]
    }
    "/projects" {
      controller = 'Project'
      action = [GET: "listProjects"]
    }
    "/project" {
      controller = 'Project'
      action = [POST: "save"]
    }
  }
}