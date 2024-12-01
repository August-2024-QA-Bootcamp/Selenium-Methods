package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*
1) extent report dependency in pom.xml file, update maven project
2) Create ExtentReportManager class and define the report
3) Create TestManager to connect test and report
4) Update Loggers class
5) Update in Base class 
*/	

public class ExtentReportManager {
	// ExtentReports build Report
	// before create this classes, please add ExtentReports dependency
	// ExtentReports dependency also required to make it workable, otherwise import doesn't work
	static ExtentReports extentReports;
	
	public static ExtentReports initialReports() {
		if(extentReports==null) {
			extentReports = new ExtentReports();
			
			// We use ExtentSparkReporter class to define our Report
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/extent_report.html");
			sparkReporter.config().setReportName("CMS Enterprise Automation Test Report"); // change accordingly
			sparkReporter.config().setDocumentTitle("Extent Report"); // title of the page
			sparkReporter.config().setTheme(Theme.DARK);
			
			// How extentReports object connect spartReporter
			extentReports.attachReporter(sparkReporter);
			extentReports.setSystemInfo("Tester", System.getProperty("user.name"));
			extentReports.setSystemInfo("OS", System.getProperty("os.name")); // OS = Operating System
			extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
			extentReports.setSystemInfo("OS Arch", System.getProperty("os.arch"));
			extentReports.setSystemInfo("Environment", "SIT"); // QA or SIT = System Integration Test, UAT = User Acceptance Test			
		}
		return extentReports;
	}
	
}
