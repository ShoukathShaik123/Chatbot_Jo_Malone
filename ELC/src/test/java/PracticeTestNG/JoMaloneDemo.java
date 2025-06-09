package PracticeTestNG;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class JoMaloneDemo {
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
		
		//To verify welcome msg and to click on track my order
		String wlcm_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_0\"]/div"))).getText();
		if(wlcm_msg.contains("Welcome")) {
			System.out.println("WELCOME MSG VERIFICATION PASSED");
			WebElement trk_My_Ord=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='chips-item-container']//button[@title='TRACK MY ORDER']")));
			trk_My_Ord.click();
		}
		
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
		
		
		//To verify the have order number msg and to click on havae order num
		String ordnum_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Do you have your order number?')]"))).getText();
		assertTrue(ordnum_msg.contains("order number"));
		System.out.println("ORDER NUMBER VERIFICATION PASSED AND CLICKED ON HAVE ORDER NUMBER");
			WebElement hav_Ord_Num=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='Have Order Number']")));
			hav_Ord_Num.click();
		
		////div[contains(text(),'Great. To proceed, please enter the numerical order number from your confirmation email')]
		
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
		
		
		
//		String ord_details=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_11\"]/div"))).getText();
//		System.out.println(ord_details);
		
		String orderID_1="418253287";
		String email_1="jayneeey@gmail.com";
		String postCode_1="DE56 2BF";
		
		String orderID_2="421331363";
		String email_2="lo_belal88@yahoo.com";
		String postCode_2="LN2 1SH";
		
		String orderID_3="237128096";
		String email_3="gloria.mma@gmail.com";
		String postCode_3="RH163bh";
					
	
		if(orderID.equals(orderID_1) && email.equals(email_1) && postCode.equals(postCode_1))
		{
//			String tnx_ord_info=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_10\"]/div"))).getText();
//			if(tnx_ord_info.contains("Thanks")) {
			String ord_details=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_11\"]/div"))).getText();
			System.out.println(ord_details);
			String order1="WILD BLUEBELL COLOGNE 30ML/1FLOZ";
			String order2="C&G CLGN INTENSE VOC 1.5ML/.05FLOZ";
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
		else if(orderID.equals(orderID_2) && email.equals(email_2) && postCode.equals(postCode_2)) {
//			String tnx_ord_info=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_10\"]/div"))).getText();
//			if(tnx_ord_info.contains("Thanks")) {
			String ord_details=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_11\"]/div"))).getText();
			System.out.println(ord_details);
			String order1="RED ROSES BATH OIL 250ML/8.5FLOZ";
			String order2="PN COLOGNE VOC 1.5ML/.05FLOZ";
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
		else if(orderID.equals(orderID_3) && email.equals(email_3) && postCode.equals(postCode_3)) {
//			String tnx_ord_info=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_10\"]/div"))).getText();
//			if(tnx_ord_info.contains("Thanks")) {
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
		
		
//		WebElement yes_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='Yes']")));
//		yes_btn.click();
		
		WebElement exp_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'How would you rate the service experience you just had?')]")));
		boolean exp_msg1=exp_msg.isDisplayed();
		assertTrue(exp_msg1);
		System.out.println("EXPERIANCE RATING MSG VERIFICATION PASSED");
			WebElement rating=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='5😃']")));
			rating.click();
		
		String feed_msg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Please share feedback about your experience with our automated assistant.')]"))).getText();
		assertTrue(feed_msg.contains("feedback"));
		System.out.println("FEED BACK MSG VEFICATION PASSED");
			WebElement min_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-lp-point='minimize_icon']")));
			min_btn.click();
			System.out.println("Minimize button is working");
		Thread.sleep(2000);
		WebElement max_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Maximize']")));
		max_btn.click();
		System.out.println("Maximize button is working");
		
		WebElement plus_icon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-lp-cust-id='actions_button']")));
		plus_icon.click();
		System.out.println("PLUS ICON IS WORKING");
		
		WebElement atch_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"LP_ActionsManagerViewController_1\"]/ul/li[1]/button")));
		atch_btn.click();
		//"C:\Users\shshaik\OneDrive - The Estée Lauder Companies Inc\Pictures\Screenshots\Screenshot (6).png"
		 StringSelection filePath = new StringSelection("\"C:\\Users\\shshaik\\OneDrive - The Estée Lauder Companies Inc\\Pictures\\Screenshots\\Screenshot (6).png\"");
	        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

	        // Use Robot class to paste and submit
	        Robot robot = new Robot();
	        robot.delay(2000);
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        
//	        WebElement element = driver.findElement(By.xpath("//div[@class='lp_input_area']"));
//	        Actions actions = new Actions(driver);
//	        actions.moveToElement(element).click().sendKeys(Keys.ENTER).perform();

	      //*[@id="lpChat"]/div[2]/div[4]/div/div[2]/div[2]/button[2]
	        WebElement snd_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lpChat\"]/div[2]/div[4]/div/div[2]/div[2]/button[2]")));
	        //snd_btn.click();//button[@class='lp_send_button']
	        Thread.sleep(3000);
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].click();", snd_btn);

	        String tnx=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Thanks for Chatting with us!')]"))).getText();
	        assertTrue(tnx.contains("Thanks"));
	        WebElement cls_btn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='End conversation']")));
	        cls_btn.click();
	        System.out.println("conversation closed");
	}
	@AfterMethod(enabled=true)
	public void closeBrowser() {
		driver.quit();
	}
	@DataProvider(name="TestData")
	public Object[][] testData(){
		return new Object[][] {
			{"418253287","jayneeey@gmail.com","DE56 2BF"},
			{"421331363","lo_belal88@yahoo.com","LN2 1SH"},
			{"237128096","gloria.mma@gmail.com","RH163bh"}
		};
	}

}
