package testCases;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	
	
	@Test(groups = {"Sanity","Master"})
	public void LoginTest() throws IOException
	{
		
		logger.info("**********TC002_AccountRegistrationTest has started the Execution************");
		
		try {
		
		
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		hp.clickLoginButton();
		
		
		
		LoginPage lp = new LoginPage(driver);
		lp.emailTxtbox(p.getProperty("email"));
		lp.pwdTxtbox(p.getProperty("password"));
		lp.loginButton();
		
		
		MyAccountPage mcc = new MyAccountPage(driver);
		boolean targetPage = mcc.myAccountDisplay();
		
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("**********TC002_AccountRegistrationTest has finished the Execution************");
	}

}
