package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.Dataproviders;



/*Data is valid  - login success - test pass  - logout
Data is valid -- login failed - test fail

Data is invalid - login success - test fail  - logout
Data is invalid -- login failed - test pass
*/


@Test(dataProvider="Logindata",dataProviderClass=Dataproviders.class)
public class TC_003_LoginDDT extends BaseClass {

	public void verify_loginDDT(String email,String password,String exp) {

		try {
			logger.info("********* Test LoginDDT case started *********");

			// HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();

			// Login Page
			LoginPage lp = new LoginPage(driver);

			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();

			// MyAccount page
			MyAccountPage acpage = new MyAccountPage(driver);
			
			if(exp.equalsIgnoreCase("Valid")) {
			if (acpage.checkMyAccountIsPresent()) {
				logger.info("MyAccount Element is present..");
				acpage.clickLogout();
				Assert.assertTrue(true);
				// Assert.assertFalse(true);
			} else {
				logger.error("********Test Failed***************MyAccount Element is not present..");
				acpage.clickLogout();
				Assert.assertFalse(true);
			}
			}
			if(exp.equalsIgnoreCase("InValid")) {
				if (acpage.checkMyAccountIsPresent()) {
					
					logger.error("********Test Failed***************MyAccount Element is present for invalid data bug..");
					acpage.clickLogout();
					Assert.assertFalse(true);
					// Assert.assertFalse(true);
				} else {
					logger.info("MyAccount Element is not present for invalid data..");
					acpage.clickLogout();
					Assert.assertFalse(false);
				}
				}
			
			

		} catch (Exception e) {
			logger.error("********Test Failed***************Exception THrown.." + e.getMessage());
			Assert.assertFalse(true);
		}
		logger.info("********* Test LoginDDT case Fininshedd *********");
	}
}