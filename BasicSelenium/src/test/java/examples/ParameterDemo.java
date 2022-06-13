package examples;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterDemo {
	
	
	@Test
	@Parameters("paraName")
	public void parameter(String paraValue)
	{
	System.out.println(paraValue);	
	}

}
