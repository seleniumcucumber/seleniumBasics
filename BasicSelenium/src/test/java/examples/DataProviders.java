package examples;

import org.testng.annotations.DataProvider;

import forTest.ExcelUtility;

public class DataProviders {
	
	ExcelUtility excel;
	
	@DataProvider(name="cucumberDataProvider")
	public Object[][] methodData1()
	{
		return new Object [][] {{"bubu"},{"Amals"},{"hello world"}};
	}
	
	
	public Object[][] getDataProviderData()
	{
		excel=new ExcelUtility();
		excel.setExcelFile("cucumber","sheetA");
		Object data[][]=new Object[3][2];
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				data[i][j] = excel.getCellData(i, j);
			}
		}
		return data;
	}
	
	@DataProvider(name = "ExcelProvider")
	public Object[][] exceldata() {
		Object data[][] = getDataProviderData();
		return data;
	}

}
