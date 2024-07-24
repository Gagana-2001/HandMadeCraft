package selenium.com.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium.com.baseTest.BaseTest;
import selenium.com.pageobjectModels.OurShopPage;
import selenium.com.pageobjectModels.PDPPage;

public class OurShopPageTest extends BaseTest {

	public OurShopPage ourShopPage;
	public PDPPage pdpPage;

	@BeforeMethod
	public void setup() throws IOException {
		ourShopPage = new OurShopPage(BaseTest.driver);
	}

	@Test(priority = 1)
	public void navigateToProductDetailPage() throws IOException, InterruptedException {
		Map<String, String> dataMap = getTestData();
		String productName = dataMap.get("ProductName");
		log.info("Searching for product and navigating to PDP page of product {}", productName);
		pdpPage = ourShopPage.navigateToProductDetailPage(productName);
	}
}