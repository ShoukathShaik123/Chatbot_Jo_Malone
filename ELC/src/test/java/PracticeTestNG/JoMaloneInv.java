package PracticeTestNG;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class JoMaloneInv {
	WebDriver driver;
	WebDriverWait wait;
	//@BeforeClass
	@BeforeMethod
	public void setUp()
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
	}
	@Test(dataProvider="TestData")
	public void nexusChat(String orderID) throws InterruptedException, AWTException {
		//To click on Nexus chat
		WebElement chat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Nexus Testing')]")));
		chat.click();
		
		//To verify welcome msg 
				String wlcm_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_0\"]/div"))).getText();
				if(wlcm_msg.contains("Welcome")) {
					System.out.println("WELCOME MSG VERIFICATION PASSED");
					
					//To click on policy link and to verify the link is navigating to correct page 
					WebElement privacy=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_0\"]/div/a")));
					privacy.click();
					String parentWind=driver.getWindowHandle();
					Set<String> windows=driver.getWindowHandles();
					for(String window:windows)
						if(!window.equals(parentWind))
							driver.switchTo().window(window);
					String title=driver.getTitle();
					if(title.contains("Privacy")) {
						System.out.println("PRIVACY POLICY VERIFICATION PASSED");
					}
					else {
						System.out.println("privacy policy verification failed");
				    }
					driver.close();
					driver.switchTo().window(parentWind);
					
					//To click on track my order
					WebElement trk_My_Ord=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='chips-item-container']//button[@title='TRACK MY ORDER']")));
					trk_My_Ord.click();
				}
				//To verify the have order number msg and to click on havae order num
				String ordnum_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Do you have your order number?')]"))).getText();
				assertTrue(ordnum_msg.contains("order number"));
				System.out.println("ORDER NUMBER VERIFICATION PASSED AND CLICKED ON HAVE ORDER NUMBER");
					WebElement hav_Ord_Num=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='Have Order Number']")));
					hav_Ord_Num.click();
					
					//To verify the msg which ask to enter order id
					//'Great. To proceed, please enter the numerical order number from your confirmation email'
					//'Perfect. Please enter the numerical order number found in your order confirmation email.
					//Perfect. Enter the numeric order number from your confirmation email.
					//Great. Please enter the numerical order number found on your order confirmation email.
				WebElement id_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Great. To proceed, please enter the numerical order number from your confirmation email') or contains(text(),'Perfect. Please enter the numerical order number found in your order confirmation email.') or contains(text(),'Perfect. Enter the numeric order number from your confirmation email.') or contains(text(),' Great. Please enter the numerical order number found on your order confirmation email.')]")));
				boolean msg1=id_msg.isDisplayed();
				if(msg1) {
					System.out.println("ORDER ID MSG VERIFICATION PASSED");
					//To enter the order id
					WebElement enter_ID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
					enter_ID.sendKeys(String.valueOf(orderID), Keys.ENTER);
				}
				//*[@id="lp_line_bubble_6"]/div
				String invMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_6\"]/div"))).getText();
				System.out.println(invMsg);
				String inv1="It looks like the order number is incorrect. You can find the numerical order number in your order confirmation email. Please try entering it again!";
				String inv2="The order number doesn't look quite right. Order Id";
				String inv3="It seems like the order number isn’t quite right. You can find your numerical order number in your order confirmation email. Please give it another try!";
				if(invMsg.contains(inv1)||invMsg.contains(inv2)||invMsg.contains(inv3)){
					System.out.println("Invalid Reply Matched");
				}
				else {
					System.out.println("Invalid Reply MisMatched");
				}
				
				WebElement enter_ID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
				enter_ID.sendKeys(String.valueOf(orderID), Keys.ENTER);
				
	}
	@DataProvider(name="TestData")
	public Object[][] testData(){
		return new Object[][] {
			{"41825328"},
		};
	}

}
