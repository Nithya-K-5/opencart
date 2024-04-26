package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

WebDriver driver;
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout_btn;
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") 
	WebElement myacount_txt;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement login_btn;
	
	public boolean checkMyAccountIsPresent() {
		try {
			
				return (myacount_txt.isDisplayed());
		}
		catch(Exception e) {
			return (false);
		}
		
	}
	
	
    public void clickLogout() {
    	logout_btn.click();
    }
}
