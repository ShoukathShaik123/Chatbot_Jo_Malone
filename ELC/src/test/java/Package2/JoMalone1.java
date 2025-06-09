package Package2;

import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JoMalone1 {
	
	WebDriver driver;
	WebDriverWait wait;
		@Test(priority=0)
		public void setUp()
		{
			try {
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
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		@Test(priority=1)
		public void nexusChat() {
			try {
			//To click on Nexus chat
			WebElement chat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Nexus Testing')]")));
			chat.click();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		@Test(priority=2)
		public void WlcmMsgVerification() {
			try {
				//To verify welcome msg and to click on track my order
				String wlcm_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_0\"]/div"))).getText();
				if(wlcm_msg.contains("Welcome")) {
					System.out.println("WELCOME MSG VERIFICATION PASSED");
					
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
						driver.close();
						driver.switchTo().window(parentWind);
						WebElement trk_My_Ord=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='chips-item-container']//button[@title='TRACK MY ORDER']")));
						trk_My_Ord.click();
					}
					else {
						System.out.println("privacy policy verification failed");
				    }
//					driver.close();
//					driver.switchTo().window(parentWind);
//					WebElement trk_My_Ord=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='chips-item-container']//button[@title='TRACK MY ORDER']")));
//					trk_My_Ord.click();
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		@Test(priority=3)
		public void haveOrdNum() {
			try {
			//To verify the have order number msg and to click on havae order num
			String ordnum_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Do you have your order number?')]"))).getText();
			String msg="Do you have your order number?";
			assertTrue(ordnum_msg.contains(msg));
			System.out.println("ORDER NUMBER VERIFICATION PASSED AND CLICKED ON HAVE ORDER NUMBER");
				WebElement hav_Ord_Num=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='Have Order Number']")));
				hav_Ord_Num.click();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		//'Great. To proceed, please enter the numerical order number from your confirmation email'
		//'Perfect. Please enter the numerical order number found in your order confirmation email.
		//Perfect. Enter the numeric order number from your confirmation email.
		//Great. Please enter the numerical order number found on your order confirmation email.
		@Test(priority=4,dataProvider="TestData")
		public void credentials(String orderID,String email,String postCode) {
			try {
				WebElement id_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Great. To proceed, please enter the numerical order number from your confirmation email') or contains(text(),'Perfect. Please enter the numerical order number found in your order confirmation email.') or contains(text(),'Perfect. Enter the numeric order number from your confirmation email.') or contains(text(),' Great. Please enter the numerical order number found on your order confirmation email.')]")));
				boolean msg1=id_msg.isDisplayed();
				if(msg1) {
					System.out.println("ORDER ID MSG VERIFICATION PASSED");
					WebElement enter_ID=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
					enter_ID.sendKeys(String.valueOf(orderID), Keys.ENTER);
				}
				
				WebElement eml_Msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Thank you! To validate the order details, please enter the email address used to place the order.')]")));
				boolean emlMsg1=eml_Msg.isDisplayed();
				if(emlMsg1) {
					System.out.println("EMAIL MSG VERIFICATION PASSED");
					WebElement enter_mail=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
					enter_mail.sendKeys(email,Keys.ENTER);
				}
				
				WebElement pscode_Msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'And the billing address postal code ')]")));
				boolean pscode1_Msg=pscode_Msg.isDisplayed();
				if(pscode1_Msg) {
					System.out.println("POSTAL CODE VERIFICATION PASSED");
					WebElement enter_pscode=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
					enter_pscode.sendKeys(postCode,Keys.ENTER);
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
						driver.close();
						driver.switchTo().window(parentWind);
					}
					else {
						System.out.println("Trackid link is not working");
				    }
//					driver.close();
//					driver.switchTo().window(parentWind);
					
					String orderID_1="418253287";
					String email_1="jayneeey@gmail.com";
					String postCode_1="DE56 2BF";
					
					//To verify whether correct order is placed or not
					if(orderID.equals(orderID_1) && email.equals(email_1) && postCode.equals(postCode_1))
					{
						String ord_details=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_11\"]/div"))).getText();
						System.out.println(ord_details);
						String order1="WILD BLUEBELL COLOGNE 30ML/1FLOZ";
						String order2="C&G CLGN INTENSE VOC 1.5ML/.05FLOZ";
						if(ord_details.contains(order1) && ord_details.contains(order2))
						{
							System.out.println("************************************************");
							System.out.println(order1 +"\n"+order2 +"\n"+"Are placed correctly");
							System.out.println("************************************************");
							
							//To click on yes button
							//Was this everything you were looking for today?
							WebElement yes_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='Yes']")));
							yes_btn.click();
						}
						else {
							
							//To click on No button
							WebElement no_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='No']")));
							no_btn.click();
						}
					}
				}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}		
		}
		@Test(priority=5)
		public void expFeedCon() {
			try {
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
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		@DataProvider(name="TestData")
		public Object[][] testData(){
			return new Object[][] {
				{"418253287","jayneeey@gmail.com","DE56 2BF"},
			};
		}
}


