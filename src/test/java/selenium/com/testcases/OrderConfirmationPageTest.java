package selenium.com.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium.com.baseTest.BaseTest;
import selenium.com.pageobjectModels.OrderConFirmationPage;

public class OrderConfirmationPageTest extends BaseTest {

	public OrderConFirmationPage orderConfPage;

	@BeforeMethod
	public void setup() {
		orderConfPage = new OrderConFirmationPage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void verifySuccessMessage() throws IOException {
		Map<String, String> dataMap = getTestData();
		String confMessage = dataMap.get("ConfMessage");
		log.info("Verifying the success message after placing the order");
		orderConfPage.verifySuccessMsg(confMessage);
	}
}