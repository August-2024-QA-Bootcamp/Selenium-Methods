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
	@FindBy(id = "cms-login-submit")
	WebElement loginButton;

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
	
	// The text which is linked to an external source is LinkText
	// Use of 'linkText' attribute as locator 
	@FindBy(linkText = "User ID")
	WebElement forgotUserId;
	
	// Use of 'partialLinkText' attribute as locator 
	@FindBy(partialLinkText = "Passwor")
	WebElement forgotPassword;
	
	// Web Element: New User Registration Button, cssSelector as locator
	@FindBy(css = "a.cms-newuser-reg")
	WebElement nurCSS;
	
	// Web Element: Login Button, cssSelector as locator
	@FindBy(css = "button#cms-login-submit")
	WebElement loginCSS;
	
	// Use of 'tagName' attribute as locator 
	@FindBy(tagName = "header")
	WebElement headerTag;
	
	@FindBy(css = "em.cms-icon.cms-sprite-loggedout.ms-3")
	WebElement logoCSS;

	public void clickLogo() {
		// common method 'clickElement()' is not used here
		logo.click();
	}

	public void clickLoginButton() throws InterruptedException {
		// common method 'pause()' is not used here
		Thread.sleep(4000);
		// common method 'clickElement()' is not used here
		loginButton.click();
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
	
	// Important: Use of linkText as locator
	// Web Element: forgot userId
	public void use_of_linkText_as_locator_in_forgot_userId_hyperlink() {
		pause(3000);
		clickElement(forgotUserId);
		pause(3000);
	}
	
	// Important: Use of partial linkText as locator
	// Web Element: forgot password
	public void use_of_partialLinkText_as_locator_in_forgot_passowrd_hyperlink() {
		pause(3000);
		clickElement(forgotPassword);
		pause(4000);
	}
	
	// Web Element: Logo
	// logo: class attribute value:  cms-icon cms-sprite-loggedout ms-3
	// Exception 'InvalidSelectorException': 'Compound class names not permitted',
	// so even if you see class value is unique but separated by space, 
	// avoid this as a class name locator, rather use css selector
	// This scenario will show error and will failed everywhere
	public void why_we_use_cssSelector_as_locator_in_logo () {
		driver.findElement(By.className("cms-icon cms-sprite-loggedout ms-3")).click();
	}
	
	// Web Element: New User Registration Button
	// Important: Use of cssSelector as locator
	// cssSelector is very important for interview
	// cssSelector by class --> inside the string, first put html tag, then dot, then write value of the class attribute 
	public void role1a_use_of_cssSelector_by_class_name_value () {
		driver.findElement(By.cssSelector("a.cms-newuser-reg")).click();
		pause(3000);
	}
	
	public void role1b_use_of_cssSelector_by_class_name_value () {
		clickElement(nurCSS);
		pause(3000);
	}
	
	// Important: Use of cssSelector as locator
	// Web Element: login Button
	// if you wanna create cssSelector by id 
	// first html tag, then # (hash), then write the value of id attribute inside the string
	public void role2a_use_of_cssSelector_by_id_value() {
		driver.findElement(By.cssSelector("button#cms-login-submit")).click();
		pause(3000);
	}
	
	public void role2b_use_of_cssSelector_by_id_value() {
		clickElement(loginCSS);
		pause(3000);
	}
	
	// Web Element: logo
	// Important: Use of cssSelector as locator
	// if the class (compound class) contain separate words [cms-icon cms-sprite-loggedout ms-3], 
	// they are actually different class, then
	// we have to close the gap between classes by putting the dot/period
	// cssSelector by class --> htmltag.class name value 
	// remove the space between words in class value, and replace with dot/period
	public void role3_use_of_cssSelector_by_compund_class_name_value () {
		driver.findElement(By.cssSelector("em.cms-icon.cms-sprite-loggedout.ms-3")).click();
	}

	// Above 3 is mostly used, 99% cases of css selector, also very common important interview question
	// below 3 high level, no need to see if you feel they are tough
	
	// Use of cssSelector as locator
	// Web Element: login Button
	// high level, you can ignore
	// cssSelector by another attribute
	// --> htmltag[id/class/name attribute = 'value of the attribute' ] , in xpath - you use // and @, which is absent here 
	// and you can use any other attribute name except id and class
	public void role4_use_of_cssSelector_by_attribute_and_its_value () {
		driver.findElement(By.cssSelector("button[name='Submit Login']")).click();
		pause(3000);
	}
	
	// Web Element: NUR Button
	// Not important, can ignore
	// cssSelector by another attribute
	// --> htmltag.value of class[id/class/name attribute = 'value of the attribute'] , in xpath you use // and @, which is absent here and you can use attribute except id and class
	// That's why we use title
	public void role5_use_of_cssSelector_by_class_name_value_and_attribute_and_its_value() {
		driver.findElement(By.cssSelector("a.cms-newuser-reg[title='New User Registration']")).click();
		pause(3000);
	}
	
	// Important: Use of cssSelector as locator
	// Web Element: login Button
	// Not important, can ignore
	// cssSelector by another attribute
	// --> htmltag#value of ID[attribute name='value'] , in xpath you use // and @, which is absent here and you can use attribute except id and class	
	public void role6_use_of_cssSelector_by_id_value_and_attribute_and_its_value() {
		driver.findElement(By.cssSelector("button#cms-login-submit[title='Login']")).click();
		pause(3000);
	}
	
	// Important: Use of tagName as locator
	// tag name: we have to go to Amazon and find "table" tag, which is unique
	
	// in cms portal not a single tag except header
	public void use_of_tagName_as_locator() {
		clickElement(headerTag);
	}
	
	// Web Element: logo
	// isDisplayed() method is boolean type, outcome: true or false
	// isDisplayed() is the method used to verify the presence of a web element within the web page.
	// Use of isDisplayed() available in --> image, link, button, text field, check box etc.
	public void use_of_isDisplayed_01() {
		boolean elementDisplayed = driver.findElement(By.cssSelector("em.cms-icon.cms-sprite-loggedout.ms-3")).isDisplayed();
		System.out.println("Is the Logo displayed? Ans: " + elementDisplayed);
	}
	
	public void use_of_isDisplayed_02() {
		logoCSS.isDisplayed();
		System.out.println(logoCSS.isDisplayed()); // we can use lik ethis way but not suggested,give a name instead
	}
	
	// use of isDisplayed method inside our common actions --> elementDisplayed()
	// Use of isDisplayed() available in --> image, link, button, text field, check box etc.
	public void use_of_isDisplayed_in_login () {
		pause(3000);
		elementDisplayed(userId);
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password);
		inputText(password, "OnthrallTest@1234");
		pause(3000);
		clickElement(termsAndCondition);
		pause(3000);
		clickElement(loginButton);
		pause(3000);
	}
	
	// Web Element: checkbox
	// isSelected() Used with radio buttons, dropdowns and checkboxes.
	// use of isSelected() method inside
	// TODO: Why showing false for isSelected?
	public void use_of_isSelected_in_login() {
		pause(3000);
		elementDisplayed(userId);
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password);
		inputText(password, "OnthrallTest@1234");
		pause(3000);
		elementSelected(termsAndCondition);
		clickElement(termsAndCondition);
		pause(3000);
		clickElement(loginButton);
		pause(3000);
	}
	
	// Web Element: login Button
	// isEnabled() is the method used to verify if the web element is enabled or
	// disabled within the web page. isEnabled() is primarily used with "buttons".
	// Use of isEnabled(), a boolean type method	
	// use of isEnabled method inside elementEnabled()
	public void use_of_isEnabled_in_login() {
		pause(3000);
		elementDisplayed(userId);
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password);
		inputText(password, "OnthrallTest@1234");
		pause(3000);
		clickElement(termsAndCondition);
		pause(3000);
		elementSelected(termsAndCondition);
		pause(3000);
		elementEnabled(loginButton);
		clickElement(loginButton);
		pause(3000);
	}

		
	
	
	
	
	
	
	

}
