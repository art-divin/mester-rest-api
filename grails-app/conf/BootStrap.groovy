import com.mester.rest.TestCase
import com.mester.rest.Project
import com.mester.rest.ProjectMarshaller
import com.mester.rest.TestCaseMarshaller
import com.mester.rest.TestStepMarshaller
import com.mester.rest.testing.CaseTestMarshaller
import com.mester.rest.testing.StepTestMarshaller
import com.mester.rest.testing.TestMarshaller
import com.mester.rest.TestStep

class BootStrap {

  def init = { servletContext ->
    [ new ProjectMarshaller(), new TestCaseMarshaller(), new TestStepMarshaller(), new CaseTestMarshaller(),
      new StepTestMarshaller(), new TestMarshaller() ].each { it.register() }
    TestCase testCase1 = new TestCase(title: "Test Case 1")
    TestCase testCase2 = new TestCase(title: "Test Case 2")
    Project project = new Project(name: "Project 1")
    project.save(flush: true)
    testCase1.setProject(project)
    testCase2.setProject(project)
    testCase1.save(flush: true)
    testCase2.save(flush: true)
    TestStep step1 = new TestStep(number: 1, text: "text for the first step")
    TestStep step2 = new TestStep(number: 2, text: "text for the second step")
    step1.setTestCase(testCase1)
    step2.setTestCase(testCase1)
    step1.save(flush: true)
    step2.save(flush: true)
    
    TestStep step3 = new TestStep(number: 1, text: "text for the first step")
    TestStep step4 = new TestStep(number: 2, text: "text for the second step")
    step3.setTestCase(testCase2)
    step4.setTestCase(testCase2)
    step3.save(flush: true)
    step4.save(flush: true)
  }
  def destroy = {
  }
}
