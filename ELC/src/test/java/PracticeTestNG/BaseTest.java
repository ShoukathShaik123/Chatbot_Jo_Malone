package PracticeTestNG;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.github.dockerjava.transport.DockerHttpClient.Request.Method;
 
public class BaseTest {
	    protected WebDriver driver;
	    WebDriverWait wait;
	 
	    @BeforeMethod
		public void setUp(Method method)
		{
			driver=new EdgeDriver();
			wait=new WebDriverWait(driver,Duration.ofSeconds(30));
			driver.get("https://wwwtmp.jomalone.co.uk/customer-service-contact-manufacturer");
			driver.manage().window().maximize();
			
			WebElement accept=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")));
			accept.click();
		
			String actTitle=driver.getTitle();
			System.out.println(actTitle);
			assert(actTitle.contains("Jo Malone"));
			System.out.println("TITLE VERIFICATION IS PASSED");
			ExtentReportMnager1.createTest(method.name());
		}
	 
	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	        ExtentReportManager.flushReports();
	    }
	 
	    @DataProvider(name="TestData")
		public Object[][] testData(){
			return new Object[][] {
				{"418253287","jayneeey@gmail.com","DE56 2BF"},
				{"421331363","lo_belal88@yahoo.com","LN2 1SH"},
				{"237128096","gloria.mma@gmail.com","RH163bh"}
			};
		}
	    public WebDriver getDriver() {
	        return driver;
	    }
	}
