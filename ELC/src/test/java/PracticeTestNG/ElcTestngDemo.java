package PracticeTestNG;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ElcTestngDemo {
	WebDriver driver;
	WebDriverWait wait;
	/*@BeforeClass
	@Parameters("browserName")
	@Test(priority=0)
	public void initialiseBrowser(String browser)
	{
		switch(browser.toLowerCase()) {
		case "chrome":
			driver=new ChromeDriver();
			break;
		case "edge":
			driver=new EdgeDriver();
			break;
		default:
			System.out.println("invalid browser");
		}
		
	}*/
	@BeforeClass
	@Test(priority=0)
	public void setUp()
	{
		driver=new EdgeDriver();
		//wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.get("https://www.esteelauder.com/");
		driver.manage().window().maximize();
		wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		WebElement close_button=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='default_dialog']//button")));
		close_button.click();
		
	}
	@Test(priority=1)
	public void intialiseWait()
	{
//		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	/*@Parameters("url")
	@Test(priority=2)
	public void launchApp(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}*/
	@Test(priority=3)
	public void closeDialougeBox()
	{
		//driver.navigate().refresh();
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
//		WebElement close_button=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='default_dialog']//button")));
//		close_button.click();
		
	}
	@Test(priority=4)
	public void bestSeller()
	{
		//driver.navigate().refresh();
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id=\"node-30\"]/div/div/div[2]/h3/span/a"))).perform();
		WebElement skinCare=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"node-193490\"]/div/ul/li[3]/a")));
		skinCare.click();
	}
	@Test(priority=5)
	public void selectProd()
	{
		//driver.navigate().refresh();
        WebElement Advanced_Night_Repair_Serum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//picture//img[contains(@alt, 'Advanced Night Repair Serum')]")));	
		
		if(Advanced_Night_Repair_Serum.isDisplayed())
		{
			System.out.println("Image validation for Advanced Night Repair Serum: PASSED");
//			Advanced_Night_Repair_Serum.click();
			Actions act = new Actions(driver);
			act.moveToElement(Advanced_Night_Repair_Serum).click().perform();
			System.out.println("Clicked on product: Advanced Night Repair Serum");
		}
		else
		{
			System.out.println("Image validation: FAILED");
		}
    	//WebElement dropDown=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='open menu']")));
	}
	@Test(priority=6)
	public void dropDown()
	{
		//driver.navigate().refresh();
		WebElement dropDown=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='open menu']")));
		dropDown.click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='1.0 oz.']"))).click();
    	
	}
	@Test(priority=7)
	public void addToBag()
	{
	//driver.navigate().refresh();
		WebElement addToBag=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@data-test-id='add_to_bag_btn']")));
    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",addToBag);
		addToBag.click();
	}
	@Test(priority=8)
	public void viewMyBag()
	{
		//driver.navigate().refresh();
		WebElement viewMyBag=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//cart-footer//span[contains(text(),' View my bag')]")));
    	viewMyBag.click();
	}
	@Test(priority=9)
	public void removeItem()
	{
		//driver.navigate().refresh();
		WebElement remove=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"remove_118860\"]")));
    	remove.click();
	}
	@Test(priority=10)
	public void backToHome()
	{
		//driver.navigate().refresh();
		WebElement home=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='continue-buttons js-continue-buttons']//a[@class='btn go-to-shopping']")));
    	home.click();
	}
	@Test(priority=11)
	public void clickOnLiveChat()
	{
		//driver.navigate().refresh();
		WebElement liveChat=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-lp-event='click']//span")));
    	liveChat.click();
	}
	@Test(priority=12)
	public void msgVerification()
	{
		//driver.navigate().refresh();
		String wlcmMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_0\"]/div"))).getText();
    	System.out.println(wlcmMsg.toLowerCase());
    	String msg=wlcmMsg.substring(0,7);
    	System.out.println(msg);
    	if(wlcmMsg.contains(msg))
    		System.out.println("live chat is opened and working");
    	else
    		System.out.println("live chat is not working");
	}
	@Test(priority=13)
	public void enterMsg()
	{
		//driver.navigate().refresh();
		WebElement typeMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
    	typeMsg.sendKeys("Hi");
	}
	@Test(priority=14)
	public void sendMsg()
	{
		//driver.navigate().refresh();
		WebElement sndMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"LP_ChatViewController_1\"]/div[2]/button[4]/span")));
    	sndMsg.click();
    	System.out.println("send button is working");
	}
	//*[@id="lp_line_bubble_2"]/div/div/div/div[1]/span
	@Test(priority=15)
	public void inquiryMsg()
	{
		//driver.navigate().refresh();
		String inqMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_2\"]/div/div/div/div[1]/span"))).getText();
		if(inqMsg.contains("inquiry")) {
			System.out.println("select your query");
			driver.navigate().refresh();
		WebElement slctQuery=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_2\"]/div/div/div/div[2]/div[2]/button")));
		slctQuery.click();
		}
		else
			System.out.println("Inquiry msg is not displayed");
	}
	//*[@id="LP_ChatViewController_1"]/div[2]/button[3]/span/svg
	//*[@id="LP_ChatViewController_1"]/div[2]/button[3]/span
	@Test(priority=16,enabled=true)
	public void clickOnEmojiBtn() throws InterruptedException
	{
		//driver.navigate().refresh();
		Thread.sleep(5000);//*[@id="lp_line_bubble_8"]/div
		String sry=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_4\"]/div"))).getText();
		if(sry.contains("sorry")) {
			//driver.navigate().refresh();
		WebElement emojiBtn=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-lp-point='emoji_button_image']")));
		emojiBtn.click();
		System.out.println("clicked on emoji button");
		}
		else
			System.out.println("sorry msg not found");
	}
	//*[@id="LP_EmojiManagerViewController_1"]/div/div/ul/li[8]/button
	@Test(priority=17,enabled=true)
	public void slctEmoji()
	{
		//driver.navigate().refresh();
		WebElement slctEmj=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("	//*[@id=\"LP_EmojiManagerViewController_1\"]/div/div/ul/li[8]/button")));
		slctEmj.click();
		System.out.println("Emoji selected");
		//driver.navigate().refresh();
		WebElement emojiBtn=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"LP_ChatViewController_1\"]/div[2]/button[3]/span")));
		emojiBtn.click();
		System.out.println("Emoji button closed");
	}
	@Test(priority=18)
	public void sndEmoji()
	{
		//driver.navigate().refresh();
		//WebElement sndEmj=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("	//*[@id=\"LP_EmojiManagerViewController_1\"]/div/div/ul/li[8]/button")));
		//sndEmj.click();
		WebElement sndEmj=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"LP_ChatViewController_1\"]/div[2]/button[4]/span")));
    	sndEmj.click();                                                                   
		System.out.println("Emoji sent");
	}
	@Test(priority=19)
	public void plusIcon() throws InterruptedException
	{
		//driver.navigate().refresh();//*[@id="LP_ChatViewController_1"]/div[2]/button[1]
		WebElement plsIcon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-lp-point='actions_button_image']")));
		plsIcon.click();
		System.out.println("clicked on plusicon");
		Thread.sleep(3000);
		//button[@class='lp_action_item_btn']
		//li[@id='LP_ForgetMeAction_1']//button[@class='lp_action_item_btn']
		//driver.navigate().refresh();
		WebElement clrHist=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='LP_ForgetMeAction_1']//button[@class='lp_action_item_btn']")));
		clrHist.click();
		System.out.println("clicked on clear history");
		//button[@data-lp-point='confirm_button']
		//driver.navigate().refresh();
		WebElement yesBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='lp_buttons_area']//button[@data-lp-point='confirm_button']")));
		yesBtn.click();
	}
	@Test(priority=20)
	public void clickOnLiveChat1()
	{
		//driver.navigate().refresh();
		WebElement liveChat=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-lp-event='click']//span")));
    	liveChat.click();
	}
	@Test(priority=21)
	public void trackOrder() throws InterruptedException
	{
		//driver.navigate().refresh();
		WebElement track=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Track My Order']")));
		track.click();
		
		//WebElement link=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_2\"]/div")));
		//link.click();
		
		String parentWind=driver.getWindowHandle();
		WebElement link=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_2\"]/div")));
		link.click();
		Set<String> windows=driver.getWindowHandles();
		for(String windId:windows)
			if(!windId.equals(parentWind))
				driver.switchTo().window(windId);
		String trkMyOrd=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/article/div/div/div/div/div[2]/div/div/div/div[1]/h1"))).getText();
		
		if(trkMyOrd.contains("Track"))
		{
			System.out.println("verification passed for track my order page");
		}
		else {
		System.out.println("verfication failed on track my order");
		}
		driver.close();
		driver.switchTo().window(parentWind);
		System.out.println("Redirected to parent window");
		
		WebElement noBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='No']")));
		noBtn.click();
		//driver.close();
		
//		driver.switchTo().window(parentWind);
//		System.out.println("Redirected to parent window");
		Thread.sleep(3000);
		
		WebElement plsIcon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-lp-point='actions_button_image']")));
		plsIcon.click();
		
		WebElement clrHist=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='LP_ForgetMeAction_3']//button[@aria-label='Clear history']")));
		clrHist.click();
		
		WebElement yesBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='lp_buttons_area']//button[@data-lp-point='confirm_button']")));
		yesBtn.click();
		
		WebElement liveChat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-lp-event='click']//span")));
    	liveChat.click();
    	
//    	WebElement rtnExch=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Start a Return/Exchange']")));
//    	rtnExch.click();
	}
	@Test(priority=22)
	public void rtnExchange()
	{
		WebElement rtnExch=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Start a Return/Exchange']")));
    	rtnExch.click();
    	
    	WebElement rtnPol=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Return Policy']")));
    	rtnPol.click();
    	
    	String rtnPol1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_5\"]/div"))).getText();
    	if(rtnPol1.contains("30 days"))
    	{
    		System.out.println("Return policy displayed correctly");
    	}
    	else
    	{
    		System.out.println("Return policy not displayed correctly");
    	}
    	
    	WebElement noBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='chips-row']//button[@title='No']")));
    	noBtn.click();
    	
    	WebElement plsIcon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-lp-point='actions_button_image']")));
		plsIcon.click();//*[@id="LP_ChatViewController_4"]/div[2]/button[1]
		
		WebElement clrHist=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"LP_ForgetMeAction_5\"]/button")));
		clrHist.click();//*[@id="LP_ForgetMeAction_5"]/button
		
		WebElement yesBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='lp_buttons_area']//button[@data-lp-point='confirm_button']")));
		yesBtn.click();
    	
	}
	@Test(priority=23)
	public void attacthments() throws AWTException, InterruptedException
	{
		WebElement liveChat=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-lp-event='click']//span")));
    	liveChat.click();
    	
    	WebElement plsIcon=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-lp-point='actions_button_image']")));
		plsIcon.click();
		
		WebElement attachBtn=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"LP_ActionsManagerViewController_7\"]/ul/li[1]/button")));
		attachBtn.click();//*[@id="LP_ActionsManagerViewController_7"]/ul/li[1]/button
		
		//C:\Users\shshaik\OneDrive - The Estée Lauder Companies Inc\Pictures\Screenshots
		//"C:\Users\shshaik\OneDrive - The Estée Lauder Companies Inc\Pictures\Screenshots\Screenshot (1).png"
		
		// Copy file path to clipboard
        StringSelection filePath = new StringSelection("\"C:\\Users\\shshaik\\OneDrive - The Estée Lauder Companies Inc\\Pictures\\Screenshots\\Screenshot (1).png\"");
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
        Thread.sleep(5000);
//        WebElement sndImg=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"lpChat\"]/div[2]/div[4]/div/div[2]/div[2]/button[1]/span")));
//    	sndImg.click();//*[@id="lpChat"]/div[2]/div[4]/div/div[2]/div[2]/button[1]/span//*[@id="lpChat"]/div[2]/div[4]/div/div[2]/div[2]/button[1]/span
        WebElement send_button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lpChat\"]/div[2]/div[4]/div/div[2]/div[2]//button[@data-lp-point='paper_plane_button']")));
		send_button.click();
		
		WebElement plsIcon1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-lp-point='actions_button_image']")));
		plsIcon1.click();
		Thread.sleep(2000);
		WebElement clrHist=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='LP_ForgetMeAction_7']//button[@aria-label='Clear history']")));
		clrHist.click();//*[@id="LP_ForgetMeAction_7"]/button
		
		WebElement yesBtn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='lp_buttons_area']//button[@data-lp-point='confirm_button']")));
		yesBtn.click();//div[@class='lp_buttons_area']//button[@data-lp-point='confirm_button']

	}
	
	
	@Test(enabled=false)
	public void closeBrowser() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.quit();
		System.out.println("Browser Closed by WebDriver");
		
	} //span[@data-lp-point='actions_button_image']
	

}
