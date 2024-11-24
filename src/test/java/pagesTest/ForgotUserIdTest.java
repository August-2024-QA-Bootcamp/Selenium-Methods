package pagesTest;

import org.testng.annotations.Test;

import baseUtil.BaseClass;

public class ForgotUserIdTest extends BaseClass {
	@Test
	public void navigateToForgotUserIdPageTest() {
		forgotUserId.navigateToForgotUserIdPage();
	}
	
	@Test()
	public void use_of_dropdown_with_selectByValue_method_test() {
		forgotUserId.navigateToForgotUserIdPage();
		forgotUserId.use_of_dropdown_with_selectByValue_method();
	}
	
	@Test()
	public void use_of_dropdown_with_selectByIndex_method_test() {
		forgotUserId.navigateToForgotUserIdPage();
		forgotUserId.use_of_dropdown_with_selectByValue_method(); // choose month
		forgotUserId.use_of_dropdown_with_selectByIndex_method(); // choose date
	}
	
	@Test ()
	public void use_of_dropdown_selectByVisibleText_method_test() {
		forgotUserId.navigateToForgotUserIdPage();
		forgotUserId.use_of_dropdown_with_selectByValue_method(); // choose month
		forgotUserId.use_of_dropdown_with_selectByIndex_method(); // choose date
		forgotUserId.use_of_dropdown_selectByVisibleText_method(); // choose year
	}
	
	// This below test is enough for the feature, above tests are single test
	// also common methods are used
	@Test()
	public void verifyForgotUserIdTest() {
		forgotUserId.navigateToForgotUserIdPage();
		forgotUserId.verifyForgotUserId();
	}

}
