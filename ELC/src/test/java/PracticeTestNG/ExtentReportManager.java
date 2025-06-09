package PracticeTestNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
public class ExtentReportManager {
	static ExtentReports extent;
    static ExtentTest test;
 
    public static ExtentReports getReportObject() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("E_reports/ExtentReport.html");
//            reporter.config().setReportName("Automation Test Results");
//            reporter.config().setDocumentTitle("Test Results");
// 
            extent = new ExtentReports();
            extent.attachReporter(reporter);
           // extent.setSystemInfo("Tester", "Your Name");
        }
        return extent;
    }

	public static void flushReports() {
		// TODO Auto-generated method stub
		
	}

}
