package selenium.com.pageobjectModels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.com.AbstractComponent.AbstractComponent;

public class OurShopPage extends AbstractComponent {

	public WebDriver driver;

	public OurShopPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='theme-product-box-container']")
	private List<WebElement> products;

	public void scrollUpToProductsDisplay() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}

	public WebElement getProduct(String productName) {
		scrollUpToProductsDisplay();
		waitForWebElementsToAppear(products);
		WebElement product = products.stream()
				.filter(prod -> prod.findElement(By.xpath("./div[3]")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		return product;
	}

	public PDPPage navigateToProductDetailPage(String productName) throws InterruptedException {
		WebElement product = getProduct(productName);
		WebElement prod = product.findElement(By.xpath("./div[1]"));
		waitForWebElementToAppear(prod);
		waitForWebElementToClickabel(prod);
		Thread.sleep(10000);
		prod.click();

		return new PDPPage(driver);
	}
}