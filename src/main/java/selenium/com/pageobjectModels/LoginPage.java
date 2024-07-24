package selenium.com.pageobjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.com.AbstractComponent.AbstractComponent;

public class LoginPage extends AbstractComponent {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "bindNotificationBar")
	private WebElement notificationBar;

	@FindBy(css = "a[href='/signin']")
	private WebElement signInLink;

	@FindBy(xpath = "//iframe[@class='signin-iframe']")
	private WebElement frame;

	@FindBy(css = "#login_id")
	private WebElement emailField;

	@FindBy(id = "nextbtn")
	private WebElement nextBtn;

	@FindBy(id = "password")
	private WebElement passwordField;

	public void navigateToLoginPage() {
		waitForWebElementToAppear(notificationBar);
		notificationBar.click();
		signInLink.click();
	}

	public HomePage login(String email, String password) {
		waitForWebElementToAppear(frame);
		driver.switchTo().frame(frame);
		emailField.sendKeys(email);
		nextBtn.click();
		waitForWebElementToAppear(passwordField);
		passwordField.sendKeys(password);
		nextBtn.click();
		driver.switchTo().defaultContent();

		return new HomePage(driver);
	}
}