package selenium.com.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium.com.baseTest.BaseTest;
import selenium.com.pageobjectModels.HomePage;
import selenium.com.pageobjectModels.OurShopPage;

public class HomePageTest extends BaseTest {

	public HomePage homePage;
	public OurShopPage ourShopPage;

	@BeforeMethod
	public void setUpPage() {
		homePage = new HomePage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void verifyLogoAtNavBar() {
		log.info("Verifying the logo at navbar in Home page");
		Boolean match = homePage.verifylogo();
		Assert.assertTrue(match);
	}

	@Test(priority = 2)
	public void verifyHeadingAtNavBar() {
		log.info("Verfiying the Heading at navbar in Home page");
		Boolean match = homePage.verifyHeading();
		Assert.assertTrue(match);
	}

	@Test(priority = 3)
	public void navigateToOurShopPage() {
		log.info("Navigating to OurShop page");
		ourShopPage = homePage.navigateToOurShopPage();
	}
}