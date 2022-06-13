package forTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import examples.DataProviders;

public class ExecuteDataPro {
	
	WebDriver driver;
	
	@BeforeMethod
	public void a()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\browserDrivers\\chromeDriver\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://www.amazon.in");
		 
	}

	@Test(dataProvider = "cucumberDataProvider",dataProviderClass = DataProviders.class)
	public void calldataProvider(String data)
	{
		WebElement field=driver.findElement(By.cssSelector("#twotabsearchtextbox"));
		 field.sendKeys(data);
	}

}
