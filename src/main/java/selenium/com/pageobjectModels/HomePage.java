package selenium.com.pageobjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.com.AbstractComponent.AbstractComponent;

public class HomePage extends AbstractComponent {

	public WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "bindNotificationBar")
	private WebElement notificationBar;

	@FindBy(xpath = "//img[@alt='Hand Made Craft']")
	private WebElement logo;

	@FindBy(css = ".theme-site-name.theme-disable-sitename-mobile")
	private WebElement heading;

	@FindBy(css = "a[href*='/our-shop/']")
	private WebElement ourShopBtn;

	public Boolean verifylogo() {
		waitForWebElementToAppear(notificationBar);
		notificationBar.click();
		Boolean logoDisplay = logo.isDisplayed();

		return logoDisplay;
	}

	public Boolean verifyHeading() {
		Boolean headingDisplay = heading.isDisplayed();

		return headingDisplay;
	}

	public OurShopPage navigateToOurShopPage() {
		ourShopBtn.click();

		return new OurShopPage(driver);
	}
}