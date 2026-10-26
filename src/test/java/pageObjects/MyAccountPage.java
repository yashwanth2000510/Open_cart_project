package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
//	locators
	
	@FindBy(xpath="//h2[text()='My Account']") WebElement myAccDisplay;
	@FindBy(xpath="(//a[text()='Logout'])[2]") WebElement lnklogoutbtn;
	
	
	
//	action methods
	
	public boolean myAccountDisplay()
	{
		try {
			
		return(myAccDisplay.isDisplayed());
		}
		catch(Exception e)
		{
			return(false);
		}
		
	}
	
	public void logoutbtn()
	{
		lnklogoutbtn.click();
	}
	
	
	

}
