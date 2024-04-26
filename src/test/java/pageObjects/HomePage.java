package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	WebDriver driver;
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement account_btn;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement register_btn;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement login_btn;
	
    public void clickMyAccount() {
    	account_btn.click();
    }
    
    public void clickRegister() {
    	register_btn.click();
    }
    
    public void clickLogin() {
    	login_btn.click();
    }
}
