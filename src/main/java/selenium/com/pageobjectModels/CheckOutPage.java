package selenium.com.pageobjectModels;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.com.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	public WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "bindNotificationBar")
	private WebElement notificationBar;

	@FindBy(id = "zs-shipping-address-selection-1")
	private WebElement shippingRadioBtn;

	@FindBy(id = "zs-toggle-billing-shipping-in-list")
	private WebElement billingCheckBox;

	@FindBy(xpath = "//form[@id='zs-address-box-form']//button[@type='button'][normalize-space()='Continue']")
	private WebElement continebtn;

	@FindBy(xpath = "//form[@class='theme-checkout-form-area']/div[1]/div[2]/div[2]/p[1]")
	private WebElement shippingBillingAddress;

	@FindBy(id = "shipping-type-01")
	private WebElement shippingMethodRadioBtn;

	@FindBy(css = "#zs-submit-shipping-method")
	private WebElement continueBtnToReviewOrder;

	@FindBy(css = "#zs-make-payment-button")
	private WebElement makePaymentBtn;

	public Boolean verifyShippingRadioButtonIsSelected() {
		waitForWebElementToAppear(notificationBar);
		notificationBar.click();
		Boolean match = shippingRadioBtn.isSelected();

		return match;
	}

	public Boolean verifyBillingAddressCheckBoxSelected() {
		Boolean match = billingCheckBox.isSelected();

		return match;
	}

	public void contineToDispatch() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		Thread.sleep(2000);
		waitForWebElementToAppear(continebtn);
		continebtn.click();
	}

	public boolean verifyShippingAndBillingAddress(String address) {
		shippingBillingAddress.getText().equalsIgnoreCase(address);

		return true;
	}

	public Boolean verifyShippingMethodIsSelected() {
		Boolean match = shippingMethodRadioBtn.isSelected();

		return match;
	}

	public void continueToReviewOrder() {
		waitForWebElementToAppear(continueBtnToReviewOrder);
		continueBtnToReviewOrder.click();
	}

	public PaymentPage navigateToPaymentPage() {
		waitForWebElementToAppear(makePaymentBtn);
		makePaymentBtn.click();

		return new PaymentPage(driver);
	}
}