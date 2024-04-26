package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class verifyHuman extends BasePage{
	
	
	public verifyHuman(WebDriver driver){
		super(driver);
	}

	@FindBy(xpath="//span[@class='ctp-label']")
	WebElement verifyhuman_txt;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement verifyHuman_chckbox;
	
	public boolean verifyHumanIsDisplayed() throws InterruptedException {
		Thread.sleep(8000);
		return verifyhuman_txt.isDisplayed();
	}
	
	public void clickverifyhumanchck() throws InterruptedException {
		Thread.sleep(4000);
		verifyHuman_chckbox.click();
	}
	
	
}
