package PracticeTestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 

public class ExtentReportMnager1 {
	

	    private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	 
	    public static ExtentReports getExtentReports() {
	        if (extent == null) {
	            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
	            extent = new ExtentReports();
	            extent.attachReporter(reporter);
	        }
	        return extent;
	    }
	 
	    public static void createTest(String testName) {
	        ExtentTest extentTest = getExtentReports().createTest(testName);
	        test.set(extentTest);
	    }
	 
	    public static ExtentTest getTest() {
	        return test.get();
	    }
	 
	    public static void flushReports() {
	        getExtentReports().flush();
	    }
	}
