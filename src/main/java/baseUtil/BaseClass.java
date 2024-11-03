package baseUtil;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.HomePage;

public class BaseClass {
	// Why default type is not ok for below 2 line? 
	// because different package accessibility is not possible for default type
	public WebDriver driver; // or we can use protected type
	public HomePage homePage; // or we can use protected type
	
	@BeforeMethod	
	public void setUp() {
		// First job is to recognize the location of driver from your Framework
		// right click on chromedriver.exe(windows)/chromedriver(mac) ---> properties ---> copy the location and paste below
		// System is a Java class and setProperty is a method of System Class		
		// 1st way, to show the location of chrome driver
		// This is an absolute path
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tofael\\eclipse-workspace\\gov.cms.portal.august2024\\driver\\chromedriver.exe");	
		// We instantiated the driver here
		driver = new ChromeDriver();
		// maximize method is used to maximize the window ---> mostly used
		driver.manage().window().maximize();
		// HTTP cookies are small blocks of data created by a web server while a user is browsing a website
		// deleteAllCookies do delete the cookies
		driver.manage().deleteAllCookies();
		// get() is used to access the url
		driver.get("https://portal.cms.gov/portal/");
		// PageLoadTimeout is used for wait to load the page for curtain amount of time
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		// Implicitly wait is used to wait for each web element
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));		
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearUp() {
		driver.quit();
	}
	
	
	
	
	
	

}
