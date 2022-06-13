package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralUtilities {
	WebDriver driver;

	public GeneralUtilities(WebDriver driver) {
		this.driver = driver;

	}
	
	public List<String> getDataOfRow(String path) {
		List<WebElement> element = driver.findElements(By.xpath(path));
		List<String> texts = new ArrayList<String>();
		for (int i = 0; i < element.size(); i++) {
			texts.add(element.get(i).getText());
		}
		return texts;
	}
	
	
	public void getTextOfOfficeOfPerson(String Name)
	{
		boolean flag=false;
		WebElement nextButton=driver.findElement(By.xpath("//a[text()='Next']"));
		List<String>names=getDataOfRow("//tr//td[1]");
		String officeXpath="";
		for(int page=1;page<=6;page++)
		{
			for (int i = 0; i < names.size(); i++) {
				names=getDataOfRow("//tr//td[1]");
				if (names.get(i).equals(Name)) {
					i++;
					officeXpath = "//tr[" + i + "]//td[3]";
					flag=true;
					break;
				}
			}
			if(flag==true)
				break;
			if(page<6)
				nextButton.click();
			nextButton=driver.findElement(By.xpath("//a[text()='Next']"));
		}
		
		System.out.println("Office ="+driver.findElement(By.xpath(officeXpath)).getText());
	}

}
