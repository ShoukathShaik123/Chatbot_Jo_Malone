package PracticeTestNG;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class JoMaloneDemo4 {

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
		public void nexusChat(String orderID,String email,String postCode) throws InterruptedException, AWTException {
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
			
			////div[contains(text(),'Great. To proceed, please enter the numerical order number from your confirmation email')]
			
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
			
			//To verify the msg which ask to enter the email
			WebElement eml_Msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Thank you! To validate the order details, please enter the email address used to place the order.')]")));
			boolean emlMsg1=eml_Msg.isDisplayed();
			if(emlMsg1) {
				System.out.println("EMAIL MSG VERIFICATION PASSED");
				//To enter the email 
				WebElement enter_mail=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
				enter_mail.sendKeys(email,Keys.ENTER);
			}
			
			//To verify the msg which ask postal code
			WebElement pscode_Msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'And the billing address postal code ')]")));
			boolean pscode1_Msg=pscode_Msg.isDisplayed();
			if(pscode1_Msg) {
				System.out.println("POSTAL CODE VERIFICATION PASSED");
				//To enter the posatal code
				WebElement enter_pscode=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
				enter_pscode.sendKeys(postCode,Keys.ENTER);
			}
			
			String orderID_3="237128096";
			String email_3="gloria.mma@gmail.com";
			String postCode_3="RH163bh";
			
			if(orderID.equals(orderID_3) && email.equals(email_3) && postCode.equals(postCode_3)) {
//				String tnx_ord_info=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_10\"]/div"))).getText();
//				if(tnx_ord_info.contains("Thanks")) {
				String ord_details=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_11\"]/div"))).getText();
				System.out.println(ord_details);
				String order1="WICK TRIMMER";
				String order2="SB&L COLOGNE 30ML/1FLOZ";
				if(ord_details.contains(order1) && ord_details.contains(order2))
				{
					System.out.println("************************************************");
					System.out.println(order1 +"\n"+order2 +"\n"+"Are placed correctly");
					System.out.println("************************************************");
					
					WebElement yes_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='Yes']")));
					yes_btn.click();
				}
				else {
					
					WebElement no_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='No']")));
					no_btn.click();
				}
				//}
			}
			
			//To print the order status
			String Status =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_11\"]/div"))).getText();         
	    	//System.out.println(Status);
	 
			String[] lines = Status.split("\\r?\\n"); // splits by line breaks
			for (String line : lines) {
			    if (line.contains("cancelled") || line.contains("on its way") || line.contains("out of stock") ||line.contains("on it's way")) {
			        System.out.println("Status of The Order : " + line);
			        System.out.println("*****************************************");
			    }
			    
			}
			//*[@id="lp_line_bubble_11"]/div/b[1]/a
			//To click on the track id link
			WebElement trackid_link=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_11\"]/div/b[1]/a")));
			trackid_link.click();
			String parentWind=driver.getWindowHandle();
			Set<String> windows=driver.getWindowHandles();
			for(String window:windows)
				if(!window.equals(parentWind))
					driver.switchTo().window(window);
			String title=driver.getTitle();
			System.out.println(title);
			if(title.contains("DHL")) {
				System.out.println("Trackid link is working");
			}
			else {
				System.out.println("Trackid link is not working");
		    }
			driver.close();
			driver.switchTo().window(parentWind);
		//To verify the service exp msg
			WebElement exp_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'How would you rate the service experience you just had?')]")));
			boolean exp_msg1=exp_msg.isDisplayed();
			assertTrue(exp_msg1);
			System.out.println("EXPERIANCE RATING MSG VERIFICATION PASSED");
			//to click on rating
				WebElement rating=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='5😃']")));
				rating.click();
			//To verify the feed back reply
			String feed_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Please share feedback about your experience with our automated assistant.')]"))).getText();
			assertTrue(feed_msg.contains("feedback"));
			System.out.println("FEED BACK MSG VEFICATION PASSED");
			
			WebElement enter_pscode=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
			enter_pscode.sendKeys("Good",Keys.ENTER);

		        String tnx=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Thanks for Chatting with us!')]"))).getText();
		        assertTrue(tnx.contains("Thanks"));
		        WebElement cls_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='End conversation']")));
		        cls_btn.click();
		        System.out.println("conversation closed");
		}
		@AfterMethod(enabled=false)
		public void closeBrowser() {
			driver.quit();
		}
		@DataProvider(name="TestData")
		public Object[][] testData(){
			return new Object[][] {
				{"237128096","gloria.mma@gmail.com","RH163bh"},
			};
		}

	}



