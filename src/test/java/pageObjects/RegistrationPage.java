package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
	
	WebDriver driver;
	
	public RegistrationPage(WebDriver driver) {
          super(driver);
	}

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstname_txt;
	
	@FindBy(xpath="//input[@id='input-lastname']") 
	WebElement lastname_txt;
	
	@FindBy(xpath="//input[@id='input-email']") 
	WebElement email_txt;
	
	@FindBy(xpath="//input[@name='telephone']") 
	WebElement telephone_txt;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password_txt;
	
	@FindBy(xpath="//input[@name='confirm']")
	WebElement passwordConfirm_txt;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement privacy_chckbox;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement continue_btn;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement registerConfirm_msg;
	
	public void setFirstName(String firstname) {
		firstname_txt.sendKeys(firstname);
	}
	
	public void setLastName(String lastname) {
		lastname_txt.sendKeys(lastname);
	}
	
	public void setEmail(String email) {
		email_txt.sendKeys(email);
	}
	
	public void setTelephone(String phoneno) {
		telephone_txt.sendKeys(phoneno);
	}
	
	public void setPassword(String password) {
		password_txt.sendKeys(password);
	}
	
	public void setConfirmPassword(String password) {
		passwordConfirm_txt.sendKeys(password);
	}
	
	public void clickPrivacyCheckBox() {
		//privacy_chckbox.click();
		elementClickByJS(privacy_chckbox);
		
	}
	
	public void clickContinue() {
		//continue_btn.click();
		elementClickByJS(continue_btn);
	}
	
	public String getConfirmationMsg() {
		String confirm_msg= registerConfirm_msg.getText();
		return confirm_msg;
	}
	
	
}
