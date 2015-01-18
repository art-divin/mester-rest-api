class UrlMappings {

  static mappings = {
    "/"(view:"/index")
    "500"(view:'/error')
    "/testcase/$id" {
      controller = "TestCase"
      action = [GET: "show", DELETE: "delete"]
    }
    "/testcase" {
      controller = "TestCase"
      action = [POST: "save"]
    }
    "/testcase/$id/step" {
      controller = "TestCase"
      action = [POST: "saveStep"]
    }
    "/step/$id" {
      controller = "TestCase"
      action = [DELETE: "deleteStep"]
    }
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
  }
}