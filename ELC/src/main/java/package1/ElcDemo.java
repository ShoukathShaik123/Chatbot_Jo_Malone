package package1;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElcDemo {
	public static void main(String[] args) throws InterruptedException  {
		WebDriver driver=new EdgeDriver();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		driver.get("https://www.esteelauder.com/");
		driver.manage().window().maximize();
		//closing popup
		WebElement close_button=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='default_dialog']//button")));
		close_button.click();
		
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//*[@id=\"node-30\"]/div/div/div[2]/h3/span/a"))).perform();
		WebElement skinCare=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"node-193490\"]/div/ul/li[3]/a")));
		skinCare.click();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,500)");
		
//		WebElement img=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//picture//img[@contains (@alt, 'Advanced Night Repair Serum')]")));
//		Actions a = new Actions(driver);
//		a.moveToElement(img).click().perform();
		WebElement Advanced_Night_Repair_Serum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//picture//img[contains(@alt, 'Advanced Night Repair Serum')]")));	
		
		if(Advanced_Night_Repair_Serum.isDisplayed())
		{
			System.out.println("Image validation for Advanced Night Repair Serum: PASSED");
//			Advanced_Night_Repair_Serum.click();
			Actions actions = new Actions(driver);
			actions.moveToElement(Advanced_Night_Repair_Serum).click().perform();
			System.out.println("Clicked on product: Advanced Night Repair Serum");
		}
		else
		{
			System.out.println("Image validation: FAILED");
		}
    	WebElement dropDown=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@aria-label='open menu']")));
//		Select s=new Select(dropDown);
//		s.selectByIndex(1);
    	dropDown.click();
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@title='1.0 oz.']"))).click();
    	WebElement addToBag=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Add To Bag')]")));
    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",addToBag);
    	addToBag.click();
    	WebElement viewMyBag=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//cart-footer//span[contains(text(),' View my bag')]")));
    	viewMyBag.click();
    	WebElement remove=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"remove_118860\"]")));
    	remove.click();
    	WebElement home=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='continue-buttons js-continue-buttons']//a[@class='btn go-to-shopping']")));
    	home.click();
    	System.out.println("Home page");
    	
    	
    	//*[@id="LPMcontainer-1746774609220-0"]/a/span
    	Thread.sleep(5000);
    	WebElement liveChat=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-lp-event='click']//span")));
    	liveChat.click();
    	//*[@id="lp_line_bubble_0"]/div
    	String wlcmMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_0\"]/div"))).getText();
    	System.out.println(wlcmMsg.toLowerCase());
    	String msg=wlcmMsg.substring(0,7);
    	System.out.println(msg);
    	if(wlcmMsg.contains(msg))
    		System.out.println("live chat is opened and working");
    	else
    		System.out.println("live chat is not working");
    	//*[@id="LP_ChatViewController_1"]/div[2]/div[3]/textarea
    	//*[@id="LP_ChatViewController_1"]/div[2]/div[3]
    	WebElement typeMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
    	typeMsg.sendKeys("Hi");
    	//*[@id="LP_ChatViewController_1"]/div[2]/button[4]/span
    	WebElement sndMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"LP_ChatViewController_1\"]/div[2]/button[4]/span")));
    	sndMsg.click();
    	//*[@id="lp_line_bubble_2"]/div/div/div/div[2]/div[2]/button
    	WebElement prodQn=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_2\"]/div/div/div/div[2]/div[2]/button")));
    	prodQn.click();
    	//*[@id="lp_line_bubble_4"]/div
    	String sryMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_4\"]/div"))).getText();
    	System.out.println(sryMsg.toLowerCase());
    	if(sryMsg.contains("sorry"))
    		System.out.println("Estee team is currently offline");
	
		
	}
}
