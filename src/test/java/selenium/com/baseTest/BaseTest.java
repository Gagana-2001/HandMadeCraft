package selenium.com.baseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import selenium.com.data.ExcelDataDriven;
import selenium.com.pageobjectModels.LoginPage;

public class BaseTest {

	public Properties prop;
	public static WebDriver driver;
	public LoginPage loginPage;
	public static Logger log;

	public WebDriver initialize() throws IOException {

		log = LogManager.getLogger(BaseTest.class);

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\selenium\\com\\resources\\Global.properties");
		prop.load(fis);
		String browser = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String browser = prop.getProperty("browser");
		log.info("Initializing {} Browser", browser);
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Tools\\Selenium Grid\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "D:\\Tools\\Selenium Grid\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("AppUrl"));
		log.info("Launching the Application");

		return driver;
	}

	@BeforeSuite
	public void launchApplication() throws IOException {
		driver = initialize();
		loginPage = new LoginPage(driver);

	}

	@AfterSuite
	public void closeConnection() {
		log.info("Closing the browser");
		driver.close();
	}

	public String getScreenShot(String testCase, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File path = new File(
				System.getProperty("user.dir") + "//Test-Result//TestNG//ScreenShots//" + testCase + ".png");
		FileUtils.copyFile(source, path);

		return System.getProperty("user.dir") + "//Test-Result//TestNG//ScreenShots//" + testCase + ".png";
	}

	public Map<String, String> getTestData() throws IOException {
		ExcelDataDriven excelDataDriven = new ExcelDataDriven();
		Map<String, String> dataMap = excelDataDriven.getData();

		return dataMap;
	}
}