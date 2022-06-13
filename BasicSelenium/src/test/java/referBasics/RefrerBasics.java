package referBasics;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import examples.DataProviders;
import forTest.ExcelUtility;
import forTest.PropHandlerDemo;
import forTest.ScreenShotUtility;

public class RefrerBasics {
	
	WebDriver driver;
	ExcelUtility excel=new ExcelUtility();
	ScreenShotUtility screenshot=new ScreenShotUtility();
	
	
	@BeforeMethod()
	public void launchBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\browserDrivers\\chromeDriver\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	
	
	@Test(enabled = false)
	public void ExcelTesting() {
		excel.setExcelFile("cucumber", "sheetA");
		System.out.println(excel.getCellData(2, 0));
		excel.setExcelFile("cucumber", "sheetB");
		System.out.println(excel.getCellData(2, 1));

		excel.setExcelFile("selenium", "demo");
		System.out.println(excel.getCellData(2, 0));
	}
	
	@Test
	public void HTTP_requestMethod() 
	{
		driver.get("https://www.amazon.in/");
		
		String url = "https://images-eu.ssl-images-amazon.com/images/G/31/img22/Electronics/Clearance/Clearance_store_Desktop_CC_2x._SY608_CB628315133_.jpg";
		int responseCode=0;
		HttpURLConnection huc = null;
		try
		{
			huc = (HttpURLConnection)(new URL(url).openConnection());
			huc.setRequestMethod("HEAD");

			huc.connect();
			responseCode=huc.getResponseCode();

		}
		catch (Exception e) {
			System.out.println("Exception");
		}
		
		System.out.println("Response code="+responseCode);
		
	}
	
	
	
	@Test
	public void screenShotTest()throws Exception
	{
		driver.get("https://www.amazon.in/");
		screenshot.takeScreenShot(driver, "");
		
		
	}
	
	
	@Test
	public void screenShotListener()throws Exception
	{
		driver.get("https://www.amazon.in/");
		WebElement field=driver.findElement(By.cssSelector("#twotabsearchtextbox"));
		 field.sendKeys("cucumber");
		Assert.assertTrue(false);
		
	}
	
	@AfterMethod(enabled = false)
	public void ItestListener(ITestResult itestresult)throws Exception
	{
		String testCaseName;
		if (itestresult.getStatus() == itestresult.FAILURE) {
			testCaseName = itestresult.getName();
			screenshot.takeScreenShot(driver, testCaseName);
		}
		driver.close();
	}
	
	@Test
	public void SwitchTo_Window()throws Exception
	{
		driver.get("https://the-internet.herokuapp.com/windows");
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
		String parentWindow=driver.getWindowHandle();
		//WebElement newWindowElemnt=driver.findElement(By.xpath("//h3[text()='New Window']"));
		//System.out.println(newWindowElemnt.getText());
		Set<String> windows=driver.getWindowHandles();
		Iterator<String> iterate=windows.iterator();
		
		while(iterate.hasNext())
		{
			String childWindow=iterate.next();
			if (!parentWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
		WebElement newWindowElemnt=driver.findElement(By.xpath("//h3[text()='New Window']"));
		System.out.println("Text displayed= "+newWindowElemnt.getText());
		driver.close();
		driver.switchTo().window(parentWindow);
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		
	}
	
	@Test
	public void SwitchToAmazon()
	{ driver.get("https://www.amazon.in/");
	  driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("mobile");
	  driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
	  driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
	  String parentWindow=driver.getWindowHandle();
	  Set<String> windows =driver.getWindowHandles();
	  for(String x:windows)
	  {
		  if(!parentWindow.equals(x))
		  {
			 driver.switchTo().window(x); 
			 driver.findElement(By.xpath("//div[@id='imgTagWrapperId']")).click();
		  }
	  }
	 //driver.findElement(By.xpath("//div[@id='imgTagWrapperId']")).click();
	}
	
	///////////////////////Actions class
	
	@Test
	public void mouseActions() throws InterruptedException
	{
		driver.get("https://demo.guru99.com/test/simple_context_menu.html");
		//driver.get("https://www.amazon.in/");
		//WebElement move=driver.findElement(By.xpath("//span[@class='nav-line-2 ']"));
		//WebElement field=driver.findElement(By.cssSelector("#twotabsearchtextbox"));
		Actions action=new Actions(driver);
		//action.moveToElement(move).build().perform();	
		//click and Hold 
		
		
		action.release().build().perform();
		
	}
	
	@Test
	public void DragAndDrop()throws Exception
	{
		driver.get("https://demo.guru99.com/test/drag_drop.html");
		WebElement dragFrom1=driver.findElement(By.xpath("//li[@class='block13 ui-draggable']//a[@class='button button-orange']"));
		WebElement dropZone=driver.findElement(By.xpath("(//div[@class='ui-widget-content']//li[@class='placeholder'])[2]"));
		Actions action=new Actions(driver);
		action.dragAndDrop(dragFrom1, dropZone).build().perform();
	}
	
	
	@Test
	public void robot_ClassExample() throws Exception
	{
		driver.get("https://www.amazon.in/");
		Robot robot=new Robot();
		WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
		//robot.keyPress(KeyEvent.VK_DOWN);
		//robot.keyPress(KeyEvent.VK_DOWN);
		//robot.keyPress(KeyEvent.VK_DOWN);
		search.sendKeys("Helooo");
		Actions action=new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(search,"a").build().perform();
	}
	
	@Test
	public void mouseSendKeys_and_Keypress()
	{
		driver.get("https://www.amazon.in/");
		WebElement search=driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("Amalxaviour");
		//search.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		Actions action=new Actions(driver);
		action.keyDown(search,Keys.CONTROL).build().perform();
		search.sendKeys("a");
		action.release(search).build().perform();
		action.sendKeys(search,Keys.BACK_SPACE).build().perform();
		//action.keyDown(Keys.CONTROL+"a");
	}
	
	
	
	@Test
	public void javaScriptExecuter_Demo()
	{
		//JavaScriptExecutor is an Interface 
		//that helps to execute JavaScript through Selenium Webdriver
		driver.get("https://www.amazon.in/");
		WebElement element=null;
		JavascriptExecutor js =  (JavascriptExecutor) driver; 
		js.executeScript("alert('Amal_Xaviour');");/////:::For Generating Alerts  
		driver.switchTo().alert().dismiss();
		
		js.executeScript("window.scrollBy(0,6000)");//for scroll down
		element=driver.findElement(By.xpath("//a[text()='Help']"));
		element.click();
		js.executeScript("arguments[0].click();",element);
	}
	
	@Test
	public void ScrollInto_View()////Scroll Into View
	{
		driver.get("https://www.browserstack.com/guide/selenium-scroll-tutorial");
		WebElement trySelenium=driver.findElement(By.xpath("//a[text()='Try Selenium Testing For Free']"));
		JavascriptExecutor js =  (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].scrollIntoView();", trySelenium);
	}
	
	@Test
	public void Amazon_ScrollIntoView()
	{
		System.out.println("Only Scroll into view");
		driver.get("https://www.amazon.in/");
		JavascriptExecutor js =  (JavascriptExecutor) driver; 
		WebElement aboutUs=driver.findElement(By.xpath("//a[text()='About Us']"));
		
		js.executeScript("arguments[0].scrollIntoView();",aboutUs);
	}
	
	@Test(enabled = false)
	public void propertiesFileTester()throws Exception
	{
		PropHandlerDemo prophandl=new PropHandlerDemo();
		System.out.println(prophandl.getPropertiesDataDemo("test1"));
		System.out.println(prophandl.getPropertiesDataDemo("amal"));
		
	}
	
	@Test(dataProvider = "ExcelProvider", dataProviderClass = DataProviders.class)
	public void ExcelDataProviderTest(String value1,String Value2) {
		System.out.println("Value 1 "+value1);
		System.out.print(" Value 2 "+Value2);
	}
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}

}
