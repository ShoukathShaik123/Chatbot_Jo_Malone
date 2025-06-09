package PracticeTestNG;
import java.time.Duration;

import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class ElcTestngDemo1 {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp()
	{
		driver=new EdgeDriver();
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.get("https://wwwtmp.jomalone.co.uk/customer-service-contact-manufacturer");
		//System.out.println("Browser is opened");
		driver.manage().window().maximize();
		
		WebElement accept=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")));
		accept.click();
	
		String actTitle=driver.getTitle();
		System.out.println(actTitle);
		assert(actTitle.contains("Jo Malone"));
		System.out.println("TITLE VERIFICATION IS PASSED");
		//WebElement accept=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")));
		//accept.click();
	}
	//Scenario 1:Tracking the order by having all valid details
	@Test(priority=1)
	public void nexusTest() {
		WebElement chat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Nexus Testing')]")));
		chat.click();

		WebElement trk_My_Ord=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='chips-item-container']//button[@title='TRACK MY ORDER']")));
		trk_My_Ord.click();
		
		WebElement hav_Ord_Num=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='Have Order Number']")));
		hav_Ord_Num.click();
		
		////div[contains(text(),'Great. To proceed, please enter the numerical order number from your confirmation email')]
		
		WebElement id_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Great. To proceed, please enter the numerical order number from your confirmation email') or contains(text(),'Perfect. Please enter the numerical order number found in your order confirmation email.') or contains(text(),'Perfect. Enter the numeric order number from your confirmation email.') or contains(text(),' Great. Please enter the numerical order number found on your order confirmation email.')]")));
		boolean msg1=id_msg.isDisplayed();
		if(msg1) {
			WebElement enter_ID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
			enter_ID.sendKeys("418253287"+Keys.ENTER);
		}
		
		WebElement eml_Msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Thank you! To validate the order details, please enter the email address used to place the order.')]")));
		boolean emlMsg1=eml_Msg.isDisplayed();
		if(emlMsg1) {
			WebElement enter_mail=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
			enter_mail.sendKeys("jayneeey@gmail.com"+Keys.ENTER);
		}
		
		WebElement pscode_Msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'And the billing address postal code ')]")));
		boolean pscode1_Msg=pscode_Msg.isDisplayed();
		if(pscode1_Msg) {
			WebElement enter_pscode=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
			enter_pscode.sendKeys("DE56 2BF"+Keys.ENTER);
		}
		
		WebElement yes_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='Yes']")));
		yes_btn.click();
		
		WebElement exp_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'How would you rate the service experience you just had?')]")));
		boolean exp_msg1=exp_msg.isDisplayed();
		if(exp_msg1) {
			WebElement rating=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='5😃']")));
			rating.click();
		}
//		driver.quit();
//		System.out.println("browser is closed");

	}
	/*@Test(priority=2)
	public void nexusChat1() {
		setUp();
		WebElement chat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Nexus Testing')]")));
		chat.click();
		
		WebElement trk_My_Ord=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='chips-item-container']//button[@title='TRACK MY ORDER']")));
		trk_My_Ord.click();
		
		WebElement hav_Ord_Num=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='Have Order Number']")));
		hav_Ord_Num.click();
		
		WebElement id_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Great. To proceed, please enter the numerical order number from your confirmation email') or contains(text(),'Perfect. Please enter the numerical order number found in your order confirmation email.') or contains(text(),'Perfect. Enter the numeric order number from your confirmation email.') or contains(text(),' Great. Please enter the numerical order number found on your order confirmation email.')]")));
		boolean msg1=id_msg.isDisplayed();
		if(msg1) {
			WebElement enter_ID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
			enter_ID.sendKeys("416164715"+Keys.ENTER);
		}
		
		WebElement eml_Msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Thank you! To validate the order details, please enter the email address used to place the order.')]")));
		boolean emlMsg1=eml_Msg.isDisplayed();
		if(emlMsg1) {
			WebElement enter_mail=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
			enter_mail.sendKeys("jayneeey@gmail.com"+Keys.ENTER);
		}
		
		WebElement pscode_Msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'And the billing address postal code ')]")));
		boolean pscode1_Msg=pscode_Msg.isDisplayed();
		if(pscode1_Msg) {
			WebElement enter_pscode=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
			enter_pscode.sendKeys("HD2 2JJ"+Keys.ENTER);
		}*/
		
		

		
	//}
}
