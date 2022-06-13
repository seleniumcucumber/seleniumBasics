package referBasics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.GeneralUtilities;

public class ObsquraTestSite {

	WebDriver driver;
	GeneralUtilities utilities;

	@BeforeMethod()
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "E:\\browserDrivers\\chromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// https://selenium.obsqurazone.com/index.php
	
	/** Group look **/
	//alwaysRun = true
			
			
	@Test(groups = "smoke")//single only 
	public void jquerySelect2() {
		driver.get("https://selenium.obsqurazone.com/jquery-select.php");
		WebElement alaska = driver.findElement(By.xpath("(//span[text()='Alaska'])[1]"));
		alaska.click();
		WebElement field = driver.findElement(By.xpath("//input[@class='select2-search__field']"));
		field.sendKeys("Arizona");
		driver.findElement(By.xpath("//li[text()='Arizona']")).click();

		driver.findElement(By.xpath("//span[@class='select2-selection select2-selection--multiple']"))
				.sendKeys("Texas");
		driver.findElement(By.xpath("//li[text()='Texas']")).click();
	}

	@Test(groups = {"smoke","regression"})
	public void bootStrapAlert() throws Exception {
		driver.get("https://selenium.obsqurazone.com/bootstrap-alert.php");
		driver.findElement(By.xpath("//button[@id='autoclosable-btn-success']")).click();
		String text = driver.findElement(By.xpath("//div[@class='alert alert-success alert-autoclosable-success']"))
				.getText();
		System.out.println("Alert Text " + text);
	}

	@Test
	public void windowPopUp()throws Exception {
		driver.get("https://selenium.obsqurazone.com/window-popup.php");
		String parent = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[@title='Follow @obsqurazone on Facebook']")).click();
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Size " + windows.size());
		Iterator<String> iter = windows.iterator();
		while (iter.hasNext()) {
			String childWindow = iter.next();
			if (!parent.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				Thread.sleep(3000);
				driver.close();

			}
		}
		driver.switchTo().window(parent);
		//driver.close();
		driver.findElement(By.xpath("//a[@title='Follow @obsqurazone']")).click();

	}

	
	@Test
	public void javScriptAlert()throws Exception
	{
		driver.get("https://selenium.obsqurazone.com/javascript-alert.php");
		driver.findElement(By.xpath("//button[text()='Click me!']")).click();
		Thread.sleep(2000);
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();
		
	}
	
	
	@Test()
	public void simpledatePicker()throws Exception
	{
		driver.get("https://selenium.obsqurazone.com/date-picker.php");
		String Date="10-02-2010";
		String split[]=Date.split("-");
		String year=split[2];
		int m=Integer.parseInt(split[1]);
		String date=split[0];
		String month="";
		
		switch (m) {
		case 01:
			month = "January";
			break;
		case 2:
			month = "February";
			break;
		case 3:
			month = "March";
			break;
		case 4:
			month = "April";
			break;
		case 5:
			month = "May";
			break;
		case 6:
			month = "June";
			break;
		case 7:
			month = "July";
			break;
		case 8:
			month = "August";
			break;
		case 9:
			month = "September";
			break;
		case 10:
			month = "October";
			break;
		case 11:
			month = "November";
			break;
		case 12:
			month = "December";
			break;
		default:
			break;
		}
		driver.findElement(By.xpath("//input[@class='form-control datepicker']")).click();
		WebElement nextButton=driver.findElement(By.xpath("//th[@class='next']"));
		WebElement prevButton=driver.findElement(By.xpath("//th[@class='prev']"));
		String month_Year=month+" "+year;
		while(true)
		{
			String actualText=driver.findElement(By.xpath("(//th[@class='datepicker-switch'])[1]")).getText();
			if(actualText.equals(month_Year))
				break;
			else if(m>4)
				nextButton.click();
			else
				prevButton.click();
		}
		driver.findElement(By.xpath("//td[@class='day' and text()='"+date+"']")).click();	
	}
	
	@Test
	public void TablePrintAll()
	{
		driver.get("https://selenium.obsqurazone.com/table-pagination.php");
		String path="//tr[@class='odd']//td[1]";
		utilities=new GeneralUtilities(driver);
		List<String>names=new ArrayList<String>();
		names=utilities.getDataOfRow(path);
		System.out.println(names);
		Assert.assertTrue(false);
		
	}
	
	@Test
	public void LocateParticularFiledCorespond()
	{
		driver.get("https://selenium.obsqurazone.com/table-pagination.php");
		utilities=new GeneralUtilities(driver);
		utilities.getTextOfOfficeOfPerson("Rhona Davidson");
		Assert.assertTrue(false);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
