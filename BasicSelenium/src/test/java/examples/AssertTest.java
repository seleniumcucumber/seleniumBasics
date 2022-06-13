package examples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertTest {
	
	SoftAssert softasert;
	WebDriver driver;
	
	@BeforeMethod
	public void launchBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\browserDrivers\\chromeDriver\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	@Test(priority = 1)
	public void case1()
	{
		driver.get("https://www.amazon.in/");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("mobile");
		 driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		 softasert=new SoftAssert();
		 //Assert.assertTrue(false);
		softasert.assertTrue(false);
		driver.navigate().to("https://www.flipkart.com/");
		 System.out.println("Heloo");
		 softasert.assertAll();
	}
	
	@Test(priority = 2)
	public void case2()
	{
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();
		Assert.assertTrue(true);
	}

}
