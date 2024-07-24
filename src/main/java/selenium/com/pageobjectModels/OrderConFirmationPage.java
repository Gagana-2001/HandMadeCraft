package selenium.com.pageobjectModels;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import selenium.com.AbstractComponent.AbstractComponent;

public class OrderConFirmationPage extends AbstractComponent {

	public WebDriver driver;

	public OrderConFirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='theme-payment-success-message']/h3")
	private WebElement successMsgEle;

	public boolean verifySuccessMsg(String confMsg) {
		successMsgEle.getText().equalsIgnoreCase(confMsg);

		return true;
	}
}