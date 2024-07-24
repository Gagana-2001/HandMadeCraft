package selenium.com.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium.com.baseTest.BaseTest;
import selenium.com.pageobjectModels.CartPage;
import selenium.com.pageobjectModels.CheckOutPage;

public class CartPageTest extends BaseTest {

	public CartPage cartPage;
	public CheckOutPage checkoutPage;

	@BeforeMethod
	public void setup() {
		cartPage = new CartPage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void verifyTheProductInCart() throws IOException {
		Map<String, String> dataMap = getTestData();
		String productName = dataMap.get("ProductName");
		log.info("Verifying the cart for product {}", productName);
		boolean match = cartPage.verifyTheProductInCart(productName);
		Assert.assertTrue(match);
	}

	@Test(priority = 2, dependsOnMethods = "verifyTheProductInCart")
	public void navigateToCheckOutPage() {
		log.info("Navigating to checkout Page");
		checkoutPage = cartPage.navigateToCheckOutPage();
	}
}