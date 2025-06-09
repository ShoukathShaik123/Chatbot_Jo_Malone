package Package2;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class ExtentReportManager {
    private static ExtentReports extent;
 
    public static ExtentReports getExtentReports() {
        if (extent == null) {
            String path = System.getProperty("user.dir")+"/test-output/ExtentReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
              reporter.config().setReportName("Test Results");
              reporter.config().setDocumentTitle("Extent Report");
 
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "Shoukath");
        }
        return extent;
    }
}
 