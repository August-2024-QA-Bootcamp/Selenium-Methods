package pagesTest;

import org.testng.annotations.Test;

import baseUtil.BaseClass;

public class HomePageTest extends BaseClass {
	// The test executed based on alphabetical order, if no priority given for couple of tests
	// (enabled = true) means The test case is not disable, it can run
	// (enabled = false) means The test case is disable, it is not running
	// priority = 1 means, this test case will run first, 2 means second .... ... so on
	
	@Test (enabled = false, priority = 1)
	public void clickLogoTest() {
		homePage.clickLogo();
	}
	
	@Test (enabled = false, priority = 4)
	public void clickForgotUserIdTest() throws InterruptedException  {
		homePage.clickForgotUserId();
	}
	
	@Test (enabled = true, priority = 2)
	public void clickUserIdTest() {
		homePage.clickUserId();
	}
	
	@Test (enabled = false, priority = 3)
	public void clickPasswordTest() {
		homePage.clickPassword();
	}
	
	@Test
	public void clickNewUserRegistrationTest() {
		homePage.clickNewUserRegistration();
	}
	
	@Test (enabled = false)
	public void clickLogoFailedTest() {
		homePage.clickLogoFailed();
	}
	// selector and locator same thing
	// org.openqa.selenium.InvalidSelectorException: Compound class names not permitted
	
	@Test
	public void clickUnlockTest() throws InterruptedException {
		homePage.clickUnlock();
	}

}
