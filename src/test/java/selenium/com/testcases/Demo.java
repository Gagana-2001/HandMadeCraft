package selenium.com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import selenium.com.baseTest.BaseTest;
import selenium.com.pageobjectModels.HomePage;
import selenium.com.pageobjectModels.OurShopPage;

public class Demo extends BaseTest {

	public HomePage homePage;
	public OurShopPage ourShopPage;

	@Test
	public void demo() {
		loginPage.navigateToLoginPage();
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
		Boolean match = homePage.verifylogo();
		Assert.assertTrue(match);
		Boolean match2 = homePage.verifyHeading();
		Assert.assertTrue(match2);
		ourShopPage = homePage.navigateToOurShopPage();
	}

}
