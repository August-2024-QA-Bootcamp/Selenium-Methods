package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


// new, you have to manually write it to get access of common actions
// this is possible when they are static in nature, * means all
// This is called static import
import static common.CommonActions.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.Set;

public class HomePage {
	WebDriver driver;
	JavascriptExecutor js;
	Actions actions;

	// parameterized constructor initialized when class in instantiated [object
	// created]
	public HomePage(WebDriver driver) {
		this.driver = driver;
		// PageFactory class help to instantiate WebElements
		// Important interview question
		PageFactory.initElements(driver, this);
		 js = (JavascriptExecutor)driver;
		 actions = new Actions(driver);
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
	
	@FindBy(xpath = "//span[text()='Help']")
	WebElement help;
	
	@FindBy(xpath = "//h1[text()='CMS Enterprise Portal - Help Center']")
	WebElement helpPageHeader;

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
	
	// In real time scenario we do below test at the beginning of a page, 
	// but by using common action, not by following below codes
	public void getMethodsOfThePage() {
		String actual = driver.getTitle();
		System.out.println("Title of the Page is: " + actual); // this line is not necessary
		String expected = "CMS Enterprise Portal";
		Assert.assertEquals(actual, expected, "Title doesn't match up");
		// if the Assertion [validation] fail, "Title doesn't match up" will show up
		
		// getCurrentUrl() is not used in homepage/landing page
		// if we direct to another page, then we have to use it
		String currentURL = driver.getCurrentUrl();
		System.out.println("Current URL: " + currentURL);
		String expectedURL= "https://portal.cms.gov/portal/";
		Assert.assertEquals(currentURL, expectedURL, "The driver failed to direct at right URL");
		
		// use of getText() in "login button"
		String actualTextPresntInTheWebElement = loginButton.getText();
		System.out.println("Text Present as: " + actualTextPresntInTheWebElement);
		String expectedTextPresntInTheWebElement = "Login";
		Assert.assertEquals(actualTextPresntInTheWebElement, expectedTextPresntInTheWebElement, "The text present in the WebElement doesn't match");	
	}
	
	// This is the first method used during automation framework
	// what is title?
	// what is the url?
	// is logo displayed?
	// here, method coming from common action
	public void newUserRegistrationPageValidation() {
		elementDisplayed(logo);
		verifyTitle(driver, "CMS Enterprise Portal");
		verifyCurrentUrl(driver, "https://portal.cms.gov/portal/");
		elementEnabled(newUserRegistration);
		verifyTextOfTheWebElement(newUserRegistration, "New User Registration");
		clickElement(newUserRegistration);
		pause(5000);
		verifyTitle(driver, "CMS Enterprise Portal - New User Registration");
		verifyCurrentUrl(driver, "https://portal.cms.gov/portal/newuserregistration");
	}
	
	// Here We used User ID field
	// getAttribute() method actually give us, the value of the Attribute, 
	// raw use, in next method we will use from common action
	public void use_of_getAttribute_method () {
		elementSelected(userId);
		pause(3000);
		String ml = userId.getAttribute("maxlength");
		System.out.println("The value of the maxlength attribute is: " + ml);
		// another example
		String ph = userId.getAttribute("placeholder");
		System.out.println("The value of the placeholder attribute is: " + ph);
	}
	
	// use of clear()
	public void use_of_clear_in_login() {
		pause(3000);
		elementDisplayed(userId);
		inputText(userId, "August 2024");
		pause(3000);
		clearTextFromTheField(userId); // new here
		pause(3000);
		inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password);
		inputText(password, "Abul Biri");
		pause(3000);
		clearTextFromTheField(password); // new here
		pause(3000);
		inputText(password, "OnthrallTest@1234");
		pause(3000);
		clickElement(termsAndCondition);
		pause(3000);
		elementSelected(termsAndCondition);
		pause(3000);
		elementEnabled(loginButton);
		verifyTextOfTheWebElement(loginButton, "Login"); // new
		clickElement(loginButton);
		pause(3000);
	}
	
	// use of navigate()
	// mostly important interview question, never used in framework or in real time environment
	public void use_of_navigate_method () {
		pause(3000);
		driver.navigate().to("https://www.mountsinai.org/");
		pause(3000);
		driver.navigate().back();
		pause(3000);
		driver.navigate().forward();
		pause(3000);
		driver.navigate().refresh();
		pause(3000);
	}
	
	// Very very important interview question
	public void use_of_mouse_hoverAction_on_ourLocations () {
		pause(3000);
		// below process we don't use in framework
		driver.navigate().to("https://www.mountsinai.org/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); 
		// use of normalize-space(text()) as an xpath is new here, please have a look
		WebElement ourLocations = driver.findElement(By.xpath("//a[normalize-space(text())='Our Locations' and @class='hidden-xs dropdown']"));
		// for mouse Hover Action
		pause(3000);
		Actions actions = new Actions(driver);
		actions.moveToElement(ourLocations).build().perform();
		pause(3000);
		// Then click on Mount Sinai Hospital from the list
		// new way to create xpath by parent [practice it]
		driver.findElement(By.xpath("//a[text()='The Mount Sinai Hospital']//parent::li[@class='notranslate']")).click();
		pause(3000);
		verifyCurrentUrl(driver, "https://www.mountsinai.org/locations/mount-sinai");		
	}
	
	public void switch_between_window() {
		elementDisplayed(help);
		clickElement(help); // a child window will open
		pause(3000);
		// getWindowHandle() method handle only one window [parent]
		// getWindowHandles() method handle more than one window
		Set<String> allWindowHandles = driver.getWindowHandles(); 
		// Extract Parent and child window from all window handles
		String parent = (String)allWindowHandles.toArray()[0]; 
		String child = (String)allWindowHandles.toArray()[1];
		driver.switchTo().window(child); // how switch to a child window
		pause(3000);
		verifyTextOfTheWebElement(helpPageHeader, "CMS Enterprise Portal - Help Center");
		pause(3000);
	}
	
	// Drop down discussed in Forgot User Id Page
		
	// use of Keys.ENTER, raw code here.
	public void use_of_sendKeys_method_then_click_by_enter_key_of_the_laptop_01 () {
		pause(3000);
		driver.findElement(By.id("cms-login-userId")).sendKeys("August 2024", Keys.ENTER);
		pause(3000);
	}
	
	// use of Keys.ENTER, common method inputTextThenClickEnter() used here
	public void use_of_sendKeys_method_then_click_by_enter_key_of_the_laptop_02 () {
		pause(3000);
		elementDisplayed(userId);
		inputTextThenClickEnter(userId, "August 2024 QA");
		pause(3000);
	}
	
	// use of Keys.RETURN , common method used
	public void use_of_sendKeys_method_then_click_by_return_key_of_the_laptop () {
		elementDisplayed(userId);
		inputTextThenClickEnter(userId, "enthrall_12");
		pause(4000);
		elementDisplayed(password);
		inputTextThenClickReturn(password, "OnthrallTest@1234"); // Used here
		pause(4000);
	}
	
	// use of Keys.TAB, common method used
	public void use_of_sendKeys_method_then_click_by_tab_key_of_the_laptop () {
		elementDisplayed(userId);
		inputTextThenClickTab(userId, "enthrall_12");  // the focus will go to next input "Password" field
		pause(4000);
	}
	
	// alternate of click()
	// very very  Important interview question + they ask you to write the code in MS word or verbally
	// JavaScriptExecutor is an Interface that helps to execute JavaScript through Selenium Webdriver. 
	// so, practice it by paper pen, then in ms word
	// when you see error message "click intercepted" or "ElementNotInteractableException", you use it
	// login button used
	public void alternate_of_click_method() {
		WebElement loginBttn = driver.findElement(By.xpath("//button[contains(@id, 'submit')]")); 
		// in the above line we created the webElement, we can also write it like line 60-90
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		// We wrote above line at the beginning of Class
		js.executeScript("arguments[0].click()", loginBttn); // memorize the content
		// arguments[0] means, find the web element of index 0, means first occurrence
		pause(4000);
	}
	
	// interview question
	// how to input text inside a field by JavascriptExecutor, alternate of sendKeys()
	// user id field is used to input text
	public void alternate_of_send_keys_method() {
		pause(3000);
		// JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='August 2024 QA' ", userId); // userId from line 48
		pause(3000);
	}
	
	// TODO: Nasir Fail, why? Test case passed but show failed after clicking button, may be the executescript lost the memory of puttin gvalue in fields
	// login process by JavascriptExecutor
	// alternative of click(), sendKeys() is used
	public void login_process_by_JavascriptExecutor_common_method(){
		elementDisplayed(userId);
		inputTextUsingJavascriptExecutor(driver, "arguments[0].value='enthrall_12'", userId);
		// inputText(userId, "enthrall_12");
		pause(3000);
		elementDisplayed(password);
		inputTextUsingJavascriptExecutor(driver, "arguments[0].value='OnthrallTest@1234'", password);
		// inputText(password, "OnthrallTest@1234");
		pause(3000);
		clickUsingJavascriptExecutor(driver, termsAndCondition);
		pause(3000);
		elementEnabled(loginButton);
		verifyTextOfTheWebElement(loginButton, "Login");
		clickUsingJavascriptExecutor(driver, loginButton);
		pause(3000);
	}
	
	// it will fail, because selenium can't handle hidden element
	public void how_to_handle_hidden_element_by_regular_selenium_method() {
		pause(3000);
		driver.navigate().to("https://www.letskodeit.com/practice");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		pause(3000);
		// identify the 'Hide' element and click on it [line 589]
		// The search field will be disappeared, but we can pass value on it, as we got the info before
		driver.findElement(By.id("hide-textbox")).click();
		pause(4000);
		// identify element and set/input text or value (line 540) by selenium
		driver.findElement(By.xpath("//input[@id='displayed-text']")).sendKeys("Hello");
		// it will fail by below error message
		// org.openqa.selenium.ElementNotInteractableException: element not interactable
		// whenever you find element not interactable in console, that is for sure a hidden element	
	}
	
	// it will pass
	public void how_to_handle_hidden_element_by_javascriptExecutor() {
		pause(3000);
		driver.navigate().to("https://www.letskodeit.com/practice");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		pause(3000);
		// identify the 'Hide' element and click on it [line 589]
		// The search field will be disappeared, but we can pass value on it, as we got the info before
		driver.findElement(By.id("hide-textbox")).click();
		pause(4000);
		// identify element and set/input text or value (line 592) by selenium
		// identify element and set/input text or value (line 612) by JavascriptExecutor
		WebElement searchField = driver.findElement(By.xpath("//input[contains(@id, 'displayed')]"));
		js.executeScript("arguments[0].value='Hello'", searchField);
		pause(3000);	
		
		// Extra Code
		// save below code for getTitle, getText of what written in field, Refresh by JavscriptExecutor
		// You can really know what was the text written by the JavascriptExecutor		
		// Not important and not related below
		// Extra code, alternative of  getText() 
		// Extra not related to hidden elements and not important
		// To find out what you send as text, not necessary for this scenario
		// Just save the below code for future reference
		String s = (String) js.executeScript("return document.getElementById('displayed-text').value");
		System.out.print("Value entered in hidden field: " + s + "\n");
		
		// Not important
		// How to get title of the page by JavaScript
		// How to read a JavaScript variable in Selenium WebDriver?
		// How to getTitle by Javascript, 
		String sTitle = js.executeScript("return document.title;").toString(); // fetching page title by javascript
		System.out.println("The title of the Page is: "+sTitle);	
		
		// Not related with this test
		// How to refresh by Javascript, 
		js.executeScript("history.go(0)"); // To do refresh by Javascript		
	}
	
	// important interview question
	// 1st way: Scroll by Actions class
	// scroll bottom and then top
	public void use_of_scroll_down_and_up_by_actions_class () {
		pause(5000);
		// for Scroll Down using Actions class, to go at the bottom of the page
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		pause(5000);
		// for Scroll Up using Actions class at the top of the page
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
		pause(5000);
		// instead of END and HOME, we can use Keys.UP or Keys.Down
		// But it doesn't change much but the test case passes, we will not use them					
	}
	
	// not important, just to know
	public void use_of_scroll_down_and_scroll_up_by_robot_class () throws InterruptedException, AWTException {
		// For some reason, they are not going completely Up or Down
		Robot robot = new Robot();
		// Scroll Down using Robot class
		robot.keyPress(KeyEvent.VK_PAGE_DOWN); // Constant for the PAGE_DOWN virtual key [VK].
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		pause(5000);
		// Scroll Up using Robot class
        robot.keyPress(KeyEvent.VK_PAGE_UP); // Constant for the PAGE_UP virtual key. 
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
        pause(5000);		
	}
	
	// important interview question
	// 2nd way: Scroll by javascriptExecutor
	// scroll in a certain position (not at the Bottom or Top)
	public void use_of_scroll_down_and_up_In_A_Certain_Pixel_by_javascriptExecutor () {
		pause(3000);
		// This will scroll down the page by 1000 pixel vertically
		// here 0 is x axis [horizontal], 1000 y axis [vertical]
		// you choose your pixel accordingly to reach to that web element
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0, 990)", "");
		// You can change the value to any pixel, and put your own to see the web element you wanna test
		pause(3000);
		js.executeScript("window.scrollBy(0, -990)", ""); // scroll up till 1000px, but not necessary based on your test
		// minus when it goes opposite of down
		pause(3000);
	}
	
	// scroll Into View The Element
	// This is very very important, standard interview question
	// This is better to use
	public void scroll_into_view_the_element() {
		WebElement enterprisePortal = driver.findElement(By.xpath("//h1[contains(text(), 'Enterprise Portal')]"));
		WebElement learnMore = driver.findElement(By.xpath("//a[text()='Learn more about Enterprise Portal']"));
		js.executeScript("arguments[0].scrollIntoView(true)", enterprisePortal);
		pause(3000);
		clickUsingJavascriptExecutor(driver, learnMore);
		pause(3000);
	}
	
	public void scroll_into_view_the_element_by_commonActions() {
		WebElement enterprisePortal = driver.findElement(By.xpath("//h1[contains(text(), 'Enterprise Portal')]"));
		WebElement learnMore = driver.findElement(By.xpath("//a[text()='Learn more about Enterprise Portal']"));
		scrollIntoViewTheElementUsingJavascriptExecutor(driver, enterprisePortal);
		pause(3000);
		clickUsingJavascriptExecutor(driver, learnMore);
		pause(3000);
	}
	
	// very very important for interview
	public void web_based_alert_accept () {
		pause(4000);
		driver.get("http://softwaretestingplace.blogspot.com/2017/03/javascript-alert-test-page.html");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("//button[contains(text(), 'Try it')]")).click();
		pause(4000);
		Alert alert = driver.switchTo().alert();
		pause(3000);
		System.out.println("The text present in the alert is: " + alert.getText()); 
		alert.accept();  // will click on OK button
		pause(3000);
		// line 720, not part of the accept function, 
		// we just added to know about, the text is present in the alert or not,
		// also if you use it after 721, it might not retrieve the text		
	}
	
	// very very important for interview
	public void web_based_alert_dismiss () {
		pause(4000);
		driver.get("http://softwaretestingplace.blogspot.com/2017/03/javascript-alert-test-page.html");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath("//button[contains(text(), 'Try it')]")).click();
		pause(4000);
		Alert alert = driver.switchTo().alert();
		pause(3000);
		System.out.println("The text present in the alert is: " + alert.getText());
		alert.dismiss(); // will click on Cancel button
		pause(3000);
	}
	
	// Only important for interview
	public void authentication_pop_up (){
		pause(3000);	
		String userName = "admin";
		String password = "admin";
		// original one is: "https://the-internet.herokuapp.com/basic_auth";
		// Updated one is: "https://admin:admin@the-internet.herokuapp.com/basic_auth";
		String url = "https://" + userName + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		pause(3000);
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		pause(3000);
		
		// The below part is not part of this test
		// identify and get text after authentication of pop up
		String t = driver.findElement(By.tagName("p")).getText(); // we use tag name as a locator in our course
		// instead above line, we can use verifyTextOfTheWebElement method
		System.out.println("The Text is: " + t);
		Assert.assertEquals(t, "Congratulations! You must have the proper credentials."); // actual vs expected
	}
	
	// This is for Enthrall IT photo upload, not needed for CMS
	public void photoIdUpload() {
		pause(4000);
		File pi = new File("./image/personalImage.jpg");
		WebElement personalImage = driver.findElement(By.xpath("//input[@name='image']"));
		personalImage.sendKeys(pi.getAbsolutePath());
		pause(4000);
		File phid = new File("./image/photoId.png");
		WebElement photoId = driver.findElement(By.xpath("//input[@name='photo_id']"));
		photoId.sendKeys(phid.getAbsolutePath());
		pause(4000);
	}

	


	
	
	
	
	
	
	

}