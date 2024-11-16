package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.asm.MemberSubstitution.FieldValue;

// new, you have to manually write it to get access of common actions
// this is possible when they are static in nature, * means all
// This is called static import
import static common.CommonActions.*;

public class HomePage {
	WebDriver driver;

	// parameterized constructor initialized when class in instantiated [object
	// created]
	public HomePage(WebDriver driver) {
		this.driver = driver;
		// PageFactory class help to instantiate WebElements
		// Important interview question
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//em[@id='cms-homepage-header-logo-unauth' and @class='cms-icon cms-sprite-loggedout ms-3']")
	WebElement logo;

	// 1st way: Most common way to write the WebElement (@FindBy),
	// you must know how to do that, 98% cases, this is used
	// Use of unique 'Id' attribute as locator
	@FindBy(id = "cms-forgot-userid")
	WebElement forgotUserId;

	// Use of unique 'name' attribute as locator
	@FindBy(name = "user-d")
	WebElement userId;
	
	// 2nd way to create a WebElement: not common, here I used for the 'password'
	// Just to make you guys familiar, if you see in your job
	@FindBy(how = How.NAME, using = "pass-d")
	WebElement password;
	
	// Use of unique  'class' attribute as locator
	// FYI: Never take a class value as unique if they have white space between words. 
	// Example: class value of logo, this is unique, but a compound class --> "cms-icon cms-sprite-loggedout ms-3"
	@FindBy(className = "cms-newuser-reg")
	WebElement newUserRegistration;
	
	// 3rd way to create a WebElement with "By" Class: 
	// Not common, here I used "unlock" web element from the home page
	// instead of xpath, we can use id, name, class etc as locator.
	// By unlock = By.id("cms-unlock-account");
	By unlock = By.xpath("//a[@id='cms-unlock-account']");
	
	// To Test implicitly wait
	@FindBy(className = "newuser-reg")
	WebElement incorrectNewUserRegistration;
	
	@FindBy(xpath = "//label[@id='cms-label-tc']")
	WebElement termsAndCondition;
	
	@FindBy(xpath = "//button[text() = 'Login' and @id='cms-login-submit']")
	WebElement loginButton;
	
	public void clickLogo() {
		// common method 'clickElement()' is not used here
		logo.click();
	}

	public void clickForgotUserId() throws InterruptedException {
		// common method 'pause()' is not used here
		Thread.sleep(4000);
		// common method 'clickElement()' is not used here
		forgotUserId.click();
		// common method 'pause()' is not used here
		Thread.sleep(4000);
	}


	// We used try-catch block to handle exception in this method
	public void clickUserId() {
		// common method 'pause()' is not used here
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// common method 'clickElement()' is used from here
		clickElement(userId);
		// common method 'pause()' is used here
		pause(3000);
	}
	
	public void clickPassword() {
		clickElement(password);
		pause(3000);
	}
	
	public void clickNewUserRegistration() {
		clickElement(newUserRegistration);
		pause(3000);
	}
	
	// Not common, just for your reference
	public void clickUnlock() throws InterruptedException {
		driver.findElement(unlock).click();
		pause(3000);
	}
	
	// How to test implicitlyWait()? or
	// How to show the exception: NoSuchElementException
	public void clickIncorrectNewUserRegistration() {
		pause(3000);
		clickElement(incorrectNewUserRegistration);		
	}
	
	// We are using sendKeys() method to input the text in userId field
	public void inputTextInUserIdField () {
		pause(3000);
		userId.sendKeys("August 2024 QA");
		pause(3000);
	}
	
	// We are using 3 common actions here -- click(), pause(), inputText()
	public void inputTextInUserIdAndPasswordFieldThenClickIAgreeAndFinallyToTheLoginButton() {
		pause(3000);
		inputText(userId, "enthrall_12");
		inputText(password, "OnthrallTest@1234");
		pause(3000);
		clickElement(termsAndCondition);
		pause(3000);
		clickElement(loginButton);
		pause(3000);
	}
	
	// Alternate of above method {Raw Code, some people like to use this way}
	// We can use a web element directly in the method, that is also common
	// we don't need to create "webElement" and "common method"
	public void useOfByClassInLoginProcess() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.name("user-d")).sendKeys("enthrall_12");
		driver.findElement(By.name("pass-d")).sendKeys("OnthrallTest@1234");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//label[@id='cms-label-tc']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[text() = 'Login' and @id='cms-login-submit']")).click();
		Thread.sleep(4000);
	}

	

}
