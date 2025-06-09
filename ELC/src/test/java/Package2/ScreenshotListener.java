//package Package2;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import org.openqa.selenium.WebDriver;
//	 
//	public class ScreenshotListener implements ITestListener {
//	 
//	    @Override
//	    public void onTestFailure(ITestResult result) {
//	        Object testClass = result.getInstance();
//	        WebDriver driver = ((BaseTest) testClass).getDriver();  // BaseTest should provide WebDriver
//	 
//	        String testName = result.getName();
//	        ScreenshotUtil.takeScreenshot(driver, testName);
//	    }
//	}
//
