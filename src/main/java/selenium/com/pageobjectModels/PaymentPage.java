package selenium.com.pageobjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import selenium.com.AbstractComponent.AbstractComponent;

public class PaymentPage extends AbstractComponent {

	public WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@autocomplete='cc-number']")
	private WebElement cardNumberField;

	@FindBy(xpath = "//div[@class='row form-group']/div[1]/select")
	private WebElement monthField;

	@FindBy(xpath = "//div[@class='row form-group']/div[2]/select")
	private WebElement yearField;

	@FindBy(xpath = "//input[@placeholder='CVV*']")
	private WebElement cvvField;

	@FindBy(name = "card_first_name")
	private WebElement fNameField;

	@FindBy(name = "card_last_name")
	private WebElement lNameField;

	@FindBy(id = "submit-button")
	private WebElement payBtn;

	public void enterPaymentDetails(String ccNumber, String month, String year, String cvvNumber, String fname,
			String lname) throws InterruptedException {
		waitForWebElementToAppear(cardNumberField);
		cardNumberField.sendKeys(ccNumber);
		Select monthDropDown = new Select(monthField);
		monthDropDown.selectByVisibleText(month);
		Select yearDropDown = new Select(yearField);
		yearDropDown.selectByVisibleText(year);
		Thread.sleep(1000);
		waitForWebElementToAppear(cvvField);
		cvvField.click();
		cvvField.sendKeys(cvvNumber);
		fNameField.sendKeys(fname);
		lNameField.sendKeys(lname);
	}

	public OrderConFirmationPage makePayment() {
		waitForWebElementToAppear(payBtn);
		payBtn.click();

		return new OrderConFirmationPage(driver);
	}
}