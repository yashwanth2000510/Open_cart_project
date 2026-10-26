package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	//constructors
	WebDriver driver;
	public AccountRegistrationPage(WebDriver driver) {
		
		super(driver);
		
	}
	
	//locators
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
	@FindBy(xpath = "//input[@id='input-lastname']") WebElement txtLastname;
	@FindBy(xpath = "//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath = "//input[@id='input-telephone']") WebElement txtTelephone;
	@FindBy(xpath = "//input[@id='input-password']") WebElement txtPassword;
	@FindBy(xpath = "//input[@id='input-confirm']") WebElement txtConfirm;
	@FindBy(xpath = "//input[@name='agree']") WebElement chkboxbtn;
	@FindBy(xpath = "//input[@value='Continue']") WebElement btnContinue;
	@FindBy(xpath = "//h1[text()='Your Account Has Been Created!']") WebElement msgConformation;
	
	
	//action methods
	public void setFirstname(String firstname)
	{
		txtFirstname.sendKeys(firstname);
	}
	
	public void setLastname(String lastname)
	{
		txtLastname.sendKeys(lastname);
	}
	
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephone)
	{
		txtTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirm(String confirm)
	{
		txtConfirm.sendKeys(confirm);
	}
	
	
	public void clickcheckbox()
	{
		chkboxbtn.click();
	}

	
	public void clickcontinue()
	{
		btnContinue.click();
	}
	
	//this is not a validation point and as we know we do not write any validation related code in the page object
	public String getConformationmsg()
	{
		try {
			return(msgConformation.getText());
		}catch (Exception e) {
			// TODO: handle exception
			return(e.getMessage());
		}
	}
}
