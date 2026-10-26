package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_AccountLoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
	public void verify_LoginDDT(String email, String pwd, String express)
	{
		logger.info("**********TC003_AccountRegistrationTest has started the Execution************");
		try 
		{
		// HomePage
		
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		hp.clickLoginButton();
		
		// Login page
		
		LoginPage lp = new LoginPage(driver);
		
		lp.emailTxtbox(email);
		lp.pwdTxtbox(pwd);
		lp.loginButton();
		
		//My Account Page
		MyAccountPage ap = new MyAccountPage(driver);
		boolean targetPage  = ap.myAccountDisplay();
		
		//valid data  --- login Successfull - test passed
		//                 login unsuccessfull - test filed
		//invalid data
		
		if(express.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				Assert.assertTrue(true);
				ap.logoutbtn();
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(express.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				Assert.assertTrue(false);
				ap.logoutbtn();
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
			
		}
	}
	
	

}
