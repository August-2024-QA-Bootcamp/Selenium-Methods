package pagesTest;

import org.testng.annotations.Test;

import baseUtil.BaseClass;

public class HomePageTest extends BaseClass {
	// The test executed based on alphabetical order, if no priority given for couple of tests
	// (enabled = true) means The test case is not disable, it can run
	// (enabled = false) means The test case is disable, it is not running
	// priority = 1 means, this test case will run first, 2 means second .... ... so on
	
	@Test (enabled = true, priority = 4)
	public void clickLogoTest() {
		homePage.clickLogo();
	}
	
	@Test (enabled = false, priority = 1)
	public void clickForgotUserIdTest() throws InterruptedException  {
		homePage.clickForgotUserId();
	}
	
	@Test (enabled = true, priority = 2)
	public void clickUserIdTest() {
		homePage.clickUserId();
	}
	
	@Test (enabled = true, priority = 3)
	public void clickPasswordTest() {
		homePage.clickPassword();
	}
	
	@Test
	public void clickNewUserRegistrationTest() {
		homePage.clickNewUserRegistration();
	}
	
	@Test
	public void clickUnlockTest() throws InterruptedException {
		homePage.clickUnlock();
	}
	
	// Test for implicitly wait and it shows No such element exception
	// org.openqa.selenium.NoSuchElementException: no such element: Unable to locate element
	@Test
	public void implicitlyWaitTest() {
		homePage.clickIncorrectNewUserRegistration();
	}
	
	// after run, disconnect the Internet, 
	// generally when Internet connection is interrupted that time pageLoadTimeout 20 seconds will be working
	@Test
	public void pageLoadTimeOutTest() throws InterruptedException {
		homePage.clickUnlock();
	}
	
	// Important interview question
	// What kind of exception you handle while creating framework?
	// You will answer: No Such Element Exception, Null Pointer Exception
	// Why you are getting Null Pointer Exception?
	// How you resolved those exception?
	// This test case will fail 
	// To test this, you have to remove PageFactory class from HomePage Constructor 
	// When PageFactory class is absent, you will find NullPointerException
	// Exception is: java.lang.NullPointerException: Cannot invoke "org.openqa.selenium.WebElement.click()" 
	// because "element" is null	
	@Test
	public void nullPointerExceptionTest() {
		homePage.clickNewUserRegistration();
	}
	
	@Test
	public void inputTextInUserIdFieldTest () {
		homePage.inputTextInUserIdField();
	}
	
	@Test
	public void inputTextInUserIdAndPasswordFieldThenClickIAgreeAndFinallyToTheLoginButtonTest() {
		homePage.inputTextInUserIdAndPasswordFieldThenClickIAgreeAndFinallyToTheLoginButton();
	}
	

	@Test
	public void useOfByClassInLoginProcessTest() throws InterruptedException {
		homePage.useOfByClassInLoginProcess();
	}
	
	
	

}
