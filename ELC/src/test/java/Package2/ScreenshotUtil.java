package Package2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
	 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
	 
public class ScreenshotUtil {
	 
	  public static String takeScreenshot(WebDriver driver, String testName) {
	      String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	      String screenshotPath = "./screenshots/" + testName + "_" + timestamp + ".png";
	 
	        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        File destFile = new File(screenshotPath);
	 
	        try {
	            destFile.getParentFile().mkdirs(); // Create directories if they don’t exist
	            Files.copy(srcFile.toPath(), destFile.toPath());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 
	        return screenshotPath;
	    }
	}


