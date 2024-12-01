package baseUtil;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import common.CommonActions;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.ForgotUserId;
import pages.HomePage;
import reports.ExtentReportManager;
import reports.TestManager;
import utils.Configuration;
import static utils.IConstant.*;

public class BaseClass {
	public WebDriver driver;
	public HomePage homePage;
	Configuration configuration;
	public ForgotUserId forgotUserId;
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	
	@BeforeSuite
	public void initialReporting() {
		extentReports = ExtentReportManager.initialReports();
	}
	
	@BeforeClass
	public void beforeClassSetUp() {
		configuration = new Configuration();
	}
	
	@BeforeMethod
	public void initialTest(Method method) {
		extentTest = TestManager.createTest(extentReports, method.getName());
		extentTest.assignCategory(method.getDeclaringClass().getName());
	} 
	
	@Parameters("browser")
	@BeforeMethod	
	public void setUp(@Optional(EDGE) String browserName) {		
		initDriver(browserName);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(configuration.getProperties(URL));
		// How can we convert a String to Long type
		long pageLoadWait = Long.parseLong(configuration.getProperties(PAGELOAD_WAIT));
		long implicitlyWait = Long.parseLong(configuration.getProperties(IMPLICITLY_WAIT));		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadWait));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));		
		initClass();
	}
	
	// TODO: Need to check again
	// BASED ON VALUE OF TESTSUITE, BROWSER WILL BE CHOOSEN
	
	// If any reason, in our test suit, parameter value is absent, 
	// default: WebdriverManager is instantiating the ChromeDriver
	
	// spelling mistake in testng.xml suite, then browser will not match and get the default one
	// default: WebdriverManager is instantiating the ChromeDriver
	
	// If we run test from any TestClass, which browser will run?
	// Edge. why? browser is absent in config.properties file, so it will take from @Optional(EDGE)
	
	public void initDriver(String browserName) {
		// String browserName = configuration.getProperties(BROWSER);
		
		switch (browserName) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		case EDGE:
			System.setProperty("webdriver.edge.driver", "./driver/msedgedriver.exe");		
			driver = new EdgeDriver();
			break;
			
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}				
	}
	
	public void initClass() {
		homePage = new HomePage(driver);
		forgotUserId = new ForgotUserId(driver);
	}
		
	@AfterMethod
	public void tearUp() {
		driver.quit();
	}
	
	@AfterMethod
	public void afterEachTest(Method method, ITestResult result) {
		for(String group: result.getMethod().getGroups()) {
			extentTest.assignCategory(group);
		}		
		if(result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test PASSED");
		}else if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.addScreenCaptureFromPath(CommonActions.getSreenShot(method.getName(), driver));
			extentTest.log(Status.FAIL, "Test FAILED");
		}else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test SKIPPED");
		}
	}
	
	@AfterSuite
	public void publishReport() {
		extentReports.flush();
	}

}
