package forTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumCode {
	@Test
	public void hello()
	{
		System.setProperty("webdriver.chrome.driver", "E:\\browserDrivers\\chromeDriver\\chromedriver.exe");
		 WebDriver driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://www.amazon.in/");
		 WebElement field=driver.findElement(By.cssSelector("#twotabsearchtextbox"));
		 field.sendKeys("cucumber");
	}

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "E:\\browserDrivers\\chromeDriver\\chromedriver.exe");
		 WebDriver driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://www.amazon.in/");
		 
		 

	}

}
