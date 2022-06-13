package forTest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShotUtility {
	
	TakesScreenshot scrShot;
	public void takeScreenShot(WebDriver driver,String name)throws Exception
	{
		scrShot =((TakesScreenshot)driver);
		 File screenShot = scrShot.getScreenshotAs(OutputType.FILE);
		 String timeStamp=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
		 
		 String destination=System.getProperty("user.dir")+"//ScreenShots//"+name+timeStamp+".png";
		 File finalDestination=new File(destination);

		 FileHandler.copy(screenShot, finalDestination);
	}

}
