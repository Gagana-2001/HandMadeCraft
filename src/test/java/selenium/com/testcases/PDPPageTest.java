package selenium.com.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium.com.baseTest.BaseTest;
import selenium.com.pageobjectModels.CartPage;
import selenium.com.pageobjectModels.PDPPage;

public class PDPPageTest extends BaseTest {

	public PDPPage pdpPage;
	public CartPage cartPage;

	@BeforeMethod
	public void setup() throws IOException {
		pdpPage = new PDPPage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void verifyTheProductName() throws IOException {
		Map<String, String> dataMap = getTestData();
		String productName = dataMap.get("ProductName");
		log.info("Verifying the product name in PDP page of product {} ", productName);
		boolean match = pdpPage.verifyTheProductDisplayed(productName);
		Assert.assertTrue(match);
	}

	@Test(priority = 2, dependsOnMethods = "verifyTheProductName")
	public void chooseColourAndSize() {
		log.info("Choosing colour");
		pdpPage.chooseColour();
		log.info("Choosing Size");
		pdpPage.chooseSize();
	}

	@Test(priority = 3, dependsOnMethods = "chooseColourAndSize")
	public void specifyTheQuantity() throws IOException {
		Map<String, String> dataMap = getTestData();
		String qty = dataMap.get("Qty");
		log.info("Specifying the Qunatity as {} ", qty);
		pdpPage.specifyTheQty(qty);
	}

	@Test(priority = 4, dependsOnMethods = "specifyTheQuantity")
	public void addProductToCart() {
		log.info("Adding product to cart");
		pdpPage.addToCart();
	}

	@Test(priority = 5, dependsOnMethods = "addProductToCart")
	public void navigateToCartPage() {
		log.info("Navigating to Cart Page");
		cartPage = pdpPage.navigateToCartPage();
	}
}