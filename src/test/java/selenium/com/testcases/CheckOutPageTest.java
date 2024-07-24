package selenium.com.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium.com.baseTest.BaseTest;
import selenium.com.pageobjectModels.CheckOutPage;
import selenium.com.pageobjectModels.PaymentPage;

public class CheckOutPageTest extends BaseTest {

	public CheckOutPage checkOutPage;
	public PaymentPage paymentPage;

	@BeforeMethod
	public void setup() {
		checkOutPage = new CheckOutPage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void verifyShippingAndBillingAdressIsSelected() {
		log.info("Verifying whether shipping address is selected");
		Boolean shippingAddress = checkOutPage.verifyShippingRadioButtonIsSelected();
		Assert.assertTrue(shippingAddress);
		log.info("Verifying whether billing address is selected");
		Boolean billingAddress = checkOutPage.verifyBillingAddressCheckBoxSelected();
		Assert.assertTrue(billingAddress);
	}

	@Test(priority = 2, dependsOnMethods = "verifyShippingAndBillingAdressIsSelected")
	public void continueToDispatch() throws InterruptedException {
		log.info("Continuing to dispatch");
		checkOutPage.contineToDispatch();
	}

	@Test(priority = 3, dependsOnMethods = "continueToDispatch")
	public void verifyShippingAndBillingAddress() throws IOException {
		Map<String, String> dataMap = getTestData();
		String address = dataMap.get("Address");
		log.info("Verifying the shipping and billing address for {}", address);
		checkOutPage.verifyShippingAndBillingAddress(address);
	}

	@Test(priority = 4, dependsOnMethods = "verifyShippingAndBillingAddress")
	public void verifyShippingMethodIsSelected() {
		log.info("Verifying whether shipping method is selected");
		Boolean match = checkOutPage.verifyShippingMethodIsSelected();
		Assert.assertTrue(match);
	}

	@Test(priority = 5, dependsOnMethods = "verifyShippingMethodIsSelected")
	public void continueToReviewOrder() {
		log.info("Continuing to review order");
		checkOutPage.continueToReviewOrder();
	}

	@Test(priority = 6, dependsOnMethods = "continueToReviewOrder")
	public void navigateToMakePayementPage() {
		log.info("Navigating to Payment Page");
		paymentPage = checkOutPage.navigateToPaymentPage();
	}
}