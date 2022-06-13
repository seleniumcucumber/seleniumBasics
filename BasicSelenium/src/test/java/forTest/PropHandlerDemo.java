package forTest;

import java.io.FileInputStream;
import java.util.Properties;

public class PropHandlerDemo {
	
	
	Properties prop;
	FileInputStream ip;
	
	public PropHandlerDemo() {
		prop = new Properties();
		try {
			ip = new FileInputStream("C:\\Users\\amalx\\eclipse-workspace\\BasicSelenium\\configDemo.properties");
			prop.load(ip);
		} catch (Exception e) {

			System.out.println("file Not found ");
		}

	}
	
	public String getPropertiesDataDemo(String key) {
		return prop.getProperty(key);
	}
	

}
