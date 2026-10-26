package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
	
	//Constructor
	
	
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
//		PageFactory.initElements(driver, this);
		
	}
	
	// Locators
	
	@FindBy(xpath="//a[@title='My Account']") 
	WebElement lnkMyAccount;
	@FindBy(xpath="//a[text()='Register']") 
	WebElement lnkRegisterBtn;
	@FindBy(xpath= "(//a[text()='Login'][1])[1]")
	WebElement lnkLogin;
	
	
	
	// acttion methods 
	public void clickMyAccount()
	{
		lnkMyAccount.click();
		
	}
	
	public void clickRegisterButton()
	{
		lnkRegisterBtn.click();
	}
	
	public void clickLoginButton()
	{
		lnkLogin.click();
	}
	
	

}
