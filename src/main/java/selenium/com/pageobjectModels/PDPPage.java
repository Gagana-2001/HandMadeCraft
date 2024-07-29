package selenium.com.pageobjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.com.AbstractComponent.AbstractComponent;

public class PDPPage extends AbstractComponent {

	public WebDriver driver;

	public PDPPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div[class='theme-product-name'] h1")
	private WebElement productNameEle;

	@FindBy(name = "qty")
	private WebElement qtyField;

	@FindBy(xpath = "//span[text()='+ Add to Cart']")
	private WebElement addToCartBtn;

	@FindBy(xpath = "//div[@data-cart-add-success='theme-cart-add-success']/div[2]/a[1]")
	private WebElement viewCartBtn;

	@FindBy(xpath = "//div[@class='theme-product-color-variations']/span[2]/label")
	private WebElement colour;

	@FindBy(xpath = "//div[@class='theme-variant-select-container']/span[1]/label")
	private WebElement size;

	public boolean verifyTheProductDisplayed(String productName) {
		waitForWebElementToAppear(productNameEle);
		productNameEle.getText().equalsIgnoreCase(productName);

		return true;
	}

	public void chooseColour() {
		colour.click();
	}

	public void chooseSize() {
		size.click();
	}

	public void specifyTheQty(String qty) {
		qtyField.clear();
		qtyField.sendKeys(qty);
	}

	public void addToCart() {
		addToCartBtn.click();
	}

	public CartPage navigateToCartPage() {
		waitForWebElementToAppear(viewCartBtn);
		viewCartBtn.click();

		return new CartPage(driver);
	}
}