package selenium.com.pageobjectModels;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.com.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {

	public WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "bindNotificationBar")
	private WebElement notificationBar;

	@FindBy(xpath = "//div[@class='theme-cart-table-row']")
	private List<WebElement> cartProducts;

	@FindBy(css = "button[class*='checkout-button']")
	private WebElement checkOutBtn;

	public Boolean verifyTheProductInCart(String productName) {
		waitForWebElementToAppear(notificationBar);
		notificationBar.click();
		waitForWebElementsToAppear(cartProducts);
		boolean match = cartProducts.stream().anyMatch(prod -> prod.findElement(By.xpath("./div[1]/div[2]/div[1]/a"))
				.getText().equalsIgnoreCase(productName + "-Olive-M"));

		return match;
	}

	public CheckOutPage navigateToCheckOutPage() {
		checkOutBtn.click();

		return new CheckOutPage(driver);
	}
}