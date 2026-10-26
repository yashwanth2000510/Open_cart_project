package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\OpenCart_TestData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrowss = xlutil.getrowcount("Sheet1");
		int totalcolumns = xlutil.getcellcount("Sheet1", 1);
		
		String logindata[][] = new String[totalrowss][totalcolumns];//created a 2 dimensional array to read rows and columns
		
		for(int i=1;i<=totalrowss;i++)
		{
			for(int j=0;j<totalcolumns;j++)
			{
				logindata[i-1][j] = xlutil.getcelldata("Sheet1", i, j);
			}
		}
		return logindata;
		
		
	}

}
