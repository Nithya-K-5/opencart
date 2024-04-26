package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='email']")
	WebElement email_txt;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password_txt;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement login_btn;
	
	public void setEmail(String email) {
		email_txt.sendKeys(email);
	}
	
	public void setPassword(String password) {
		password_txt.sendKeys(password);
	}
	
	
    public void clickLogin() {
    	login_btn.click();
    }
}
