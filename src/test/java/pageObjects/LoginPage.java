package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
	
	
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
//		PageFactory.initElements(driver, this);
	}
	
	// locators
	
	@FindBy(xpath="//input[@name='email']") WebElement emailFeild;
	@FindBy(xpath= "//input[@name='password']") WebElement pwdFeild;
	@FindBy(xpath="//input[@value='Login']") WebElement lgnbtn;
	
	
//	action class
	
	public void emailTxtbox(String email)
	{
		emailFeild.sendKeys(email);
	}
	
	public void pwdTxtbox(String pwd)
	{
		pwdFeild.sendKeys(pwd);
	}
	
	public void loginButton()
	{
		lgnbtn.click();
	}

}
