package PracticeTestNG;

import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
 

//public class TestListener implements ITestListener{
	
	
	public class TestListener implements ITestListener {
	 
	    @Override
	    public void onTestFailure(ITestResult result) {
	        Object testClass = result.getInstance();
	        WebDriver driver = ((BaseTest) testClass).getDriver();
	 
	        String path = ScreenshotsUtil.takeScreenshot(driver, result.getName());
	 
	        ExtentReportMnager1.getTest().fail("Test failed: " + result.getThrowable(),
	                MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	    }
	 
	    @Override
	    public void onTestSuccess(ITestResult result) {
	        ExtentReportMnager1.getTest().pass("Test passed");
	    }
	}