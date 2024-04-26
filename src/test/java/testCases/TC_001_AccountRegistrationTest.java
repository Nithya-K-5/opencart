package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import pageObjects.verifyHuman;
import testBase.BaseClass;

//@Listeners(utilities.ExtentReportManager.class)
public class TC_001_AccountRegistrationTest extends BaseClass{

	
	@Test(groups= {"sanity","master"})
	public void verify_AccountRegistration() throws InterruptedException {
		
//		verifyHuman vh=new verifyHuman(driver);
//		if(vh.verifyHumanIsDisplayed()) {
//			vh.clickverifyhumanchck();
//		}
		logger.debug("Applictions log starts......");
		logger.info("******* Starting TC_001_AccountRegistrationTest method **********");
		
		Thread.sleep(4000);
		
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked MyAccount");
		hp.clickRegister();
		logger.info("Clicked Register link");
		
		RegistrationPage regpage= new RegistrationPage(driver);
		logger.info("------------Entering customers details------------");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString());
		
		regpage.setEmail(randomeString()+"@gmail.com");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.clickPrivacyCheckBox();
		regpage.clickContinue();
		logger.info("Clicked on Continue.......");
		
		
		String confmsg=regpage.getConfirmationMsg();
		
		logger.info("Validating Confirm message with expected ");
		
		if(confmsg.equals("Your Account Has Been Created!")) {
		    Assert.assertTrue(true);
		}
		else {
			logger.error("*********  Test failed  *****************");
			Assert.fail();
			
		}
		}
		catch(Exception e) {
			logger.error("*********  Test failed  *****************"+e.getMessage());
			Assert.fail();
		}
		
		logger.debug("Applictions log endss......");
		logger.info("*******Finished TC_001_AccountRegistrationTest method**********");
	}
	

	
	
	
}
