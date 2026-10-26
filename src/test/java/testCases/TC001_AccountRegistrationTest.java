package testCases;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	
	
	@Test(groups = {"Regression","Master"})
	public void Verify_Account_Registration()
	{
//		SoftAssert se = new SoftAssert();
		logger.info("**********TC001_AccountRegistrationTest has started the Execution************");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		logger.info("**********Clicked on the My Account************");

		hp.clickRegisterButton();
		logger.info("**********Clicked on the Register now button************");
		
		logger.info("**********providing the user details************");
		AccountRegistrationPage arp = new AccountRegistrationPage(driver);
		arp.setFirstname(randomstring().toUpperCase());
		arp.setLastname(randomstring().toUpperCase());
		arp.setEmail(randomstring()+"@gmail.com");
		arp.setTelephone(randomnumber());
		
		
		String password = randompwd();
		
		arp.setPassword(password);
		arp.setConfirm(password);
		
		arp.clickcheckbox();
		arp.clickcontinue();
		
		logger.info("**********perfroming the validation************");
		
		String cfmmsg = arp.getConformationmsg();
		
		
//		se.assertEquals(cfmmsg, "Your Account Has Been Created!!!!!");
		
		
//		Assert.assertEquals(cfmmsg, "Your Account Has Been Created!");
		if(cfmmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("test Failed");
			logger.debug("Debugs logged");
			Assert.assertTrue(false);
		}
		}
		catch(Exception e)
		{
			
			Assert.fail();
		
			
		}
//		se.assertAll();
	
		logger.info("**********TC001_AccountRegistrationTest has finished the Execution************");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String randomstring()
	{
		String generatedstring = RandomStringUtils.randomAlphanumeric(5);
		return generatedstring;
	}
	
	public String randomnumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randompwd()
	{
		String generatedpwd = RandomStringUtils.randomAlphanumeric(8);
		return generatedpwd;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
