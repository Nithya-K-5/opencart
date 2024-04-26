package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass {

	@Test(groups= {"regression","master"})
	public void verify_login() {

		try {
			logger.info("********* Test Login case started *********");

			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("My Account clicked");
			hp.clickLogin();
			logger.info("Login clicked");

			// Login Page
			LoginPage lp = new LoginPage(driver);

			logger.info("Entering login details...");
			lp.setEmail(property.getProperty("email"));
			lp.setPassword(property.getProperty("password"));
			lp.clickLogin();
			logger.info("Login button clicked..");

			// MyAccount page
			MyAccountPage acpage = new MyAccountPage(driver);
			logger.info("Validating MyAccount Element ..");
			if (acpage.checkMyAccountIsPresent()) {
				logger.info("MyAccount Element is present..");
				Assert.assertTrue(true);
				// Assert.assertFalse(true);
			} else {
				logger.error("********Test Failed***************MyAccount Element is not present..");
				Assert.assertFalse(true);
			}

		} catch (Exception e) {
			logger.error("********Test Failed***************Exception THrown.." + e.getMessage());
			Assert.assertFalse(true);
		}
		logger.info("********* Test Login case Fininshedd *********");
	}
}
