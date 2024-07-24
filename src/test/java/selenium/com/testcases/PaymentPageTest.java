package selenium.com.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium.com.baseTest.BaseTest;
import selenium.com.pageobjectModels.OrderConFirmationPage;
import selenium.com.pageobjectModels.PaymentPage;

public class PaymentPageTest extends BaseTest {

	public PaymentPage paymentPage;
	public OrderConFirmationPage orderConfPage;

	@BeforeMethod
	public void setup() {
		paymentPage = new PaymentPage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void enterPaymentDetails() throws InterruptedException, IOException {
		Map<String, String> dataMap = getTestData();
		String cardNumber = dataMap.get("CardNumber");
		String month = dataMap.get("Month");
		String year = dataMap.get("Year");
		String cvv = dataMap.get("CVV");
		String firstName = dataMap.get("FirstName");
		String lastName = dataMap.get("LastName");
		log.info("Entering payment details- Card Number:{}", cardNumber);
		log.info("Entering payment details-Month:{}", month);
		log.info("Entering payment details-Year:{}", year);
		log.info("Entering payment details-CVV:{}", cvv);
		log.info("Entering payment details-FirstName:{}", firstName);
		log.info("Entering payment details-LastName:{}", lastName);
		paymentPage.enterPaymentDetails(cardNumber, month, year, cvv, firstName, lastName);
	}

	@Test(priority = 2, dependsOnMethods = "enterPaymentDetails")
	public void makePayment() {
		log.info("Making payment and placing the order");
		orderConfPage = paymentPage.makePayment();
	}
}