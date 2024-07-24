package selenium.com.testcases;

import org.testng.annotations.Test;

import selenium.com.baseTest.BaseTest;
import selenium.com.pageobjectModels.HomePage;

public class LoginPageTest extends BaseTest {

	public HomePage homePage;

	@Test(priority = 1)
	public void navigateToLoginPage() {
		log.info("Navigating to Login Page");
		loginPage.navigateToLoginPage();
	}

	@Test(priority = 2, dependsOnMethods = "navigateToLoginPage")
	public void login() {
		log.info("Logging in with valid credentials email {} password {}", prop.getProperty("email"),
				prop.getProperty("password"));
		homePage = loginPage.login(prop.getProperty("email"), prop.getProperty("password"));
	}
}