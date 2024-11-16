package baseUtil;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;

public class BaseClass {
	// Why default type is not ok for below 2 line? 
	// because different package accessibility is not possible for default type
	public WebDriver driver; // or we can use protected type
	// public ChromeDriver driver;
	// public FirefoxDriver driver;
	// public EdgeDriver driver;
	public HomePage homePage; // or we can use protected type
	
	@BeforeMethod	
	public void setUp() {
		// First job is to recognize the location of driver from your Framework
		// right click on chromedriver.exe(windows)/chromedriver(mac) ---> properties ---> copy the location and paste below
		// System is a Java class and setProperty is a method of System Class		
		
		// 1st way, to show the location of chrome driver
		// This is an absolute path
		// System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tofael\\eclipse-workspace\\gov.cms.portal.august2024\\driver\\chromedriver.exe");	
		
		// 2nd way, to show the location of the chrome driver
		// This is a dynamic way (relative path)
		// user.dir means --> System set the property to User Directory, hence till the project
		// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
		
		// 3rd and final way, to show the location of chrome driver
		// This is a dynamic path (relative path)
		// I will use this one, till end of the course
		
		// For Chrome Driver
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		
		// For Firefox Driver
		// System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		// driver = new FirefoxDriver();
		
		// For Edge Driver
		// System.setProperty("webdriver.edge.driver", "./driver/msedgedriver.exe");		
		// driver = new EdgeDriver();
			
		// We need to add the WebDriverManager dependency in the pom.xml file
		// When physical driver absent, or driver is not  working, because of version issue, then you can use WebDriverManager
		// WebDriverManager doesn't need any physical driver
		// From below line, the most updated version of Chrome browser will be initialized, when no version is mentioned	
		
		// WebDriverManager.chromedriver().setup();
		// driver = new ChromeDriver();
		
		// WebDriverManager.firefoxdriver().setup();
		// driver = new FirefoxDriver();

		// WebDriverManager.edgedriver().setup();
		// driver = new EdgeDriver();
		
		// present version: 131.0.6778.69
		// stable version: 131.0.6778.69
		// older version: 127.0.6533.72, 125.0.6422.78, 124.0.6422.78 [used here]
		// older version sometimes used for stability of browser, sometime the request from the Authority
		// if you choose version, then it will use that specific version of driver
		// WebDriverManager.chromedriver().driverVersion("124.0.6422.78").setup();
		// driver = new ChromeDriver();
		
		// maximize method is used to maximize the window ---> mostly used
		driver.manage().window().maximize();
		// We can also use fullscreen() instead of maximize() method
		// while using fullscreen method, move driver. get before fullscreen method
		// driver.manage().window().fullscreen();
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
