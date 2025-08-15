package Com.ShoppersStack_GenericUtility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Com.ShoppersStack_POM.Home_Page;
import Com.ShoppersStack_POM.Login_Page;
import Com.ShoppersStack_POM.Welcome_Page;

public class BaseTest {

	public WebDriver driver;
	public static WebDriver sDriver;
	public FileUtility fileUtility = new FileUtility();
	public ExtentSparkReporter spark;
	public ExtentReports reports;
	public ExtentTest test;
	public Welcome_Page welcomePage;
	public Login_Page loginPage;
	public Home_Page homePage;
	public WebDriverWait wait;
	public WebDriverUtility webDriverUtility = new WebDriverUtility();

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("@BeforeSuite ------------ DataBase");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("@BeforeTest ------------- ExtentReports Start");

		spark = new ExtentSparkReporter(FrameWorkConstants.reportsPath);
		reports = new ExtentReports();
		reports.attachReporter(spark);
		test = reports.createTest("Address");
	}

	@BeforeClass
	public void beforeClass() throws IOException {
		System.out.println("@BeforeClass -------------- Launching Browser");
		String browserName = fileUtility.readDataFromPropertFile("browserName");
		String baseUrl = fileUtility.readDataFromPropertFile("url");

//		String browserName = System.getProperty("browser");
//		String baseUrl = System.getProperty("url");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Please Provide Valid Browser Name");
			throw new RuntimeException();
		}
		sDriver = driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(baseUrl);
	}

	@BeforeMethod
	public void beforeMethod() throws IOException, InterruptedException {
		System.out.println("@BeforeMethod --------------- Login");

		welcomePage = new Welcome_Page(driver);

		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(welcomePage.getLoginBtn()));
		Thread.sleep(3000);

		welcomePage.getLoginBtn().click();

		loginPage = new Login_Page(driver);
		loginPage.getEmailTextField().sendKeys(fileUtility.readDataFromPropertFile("username"));
		loginPage.getPasswordTextField().sendKeys(fileUtility.readDataFromPropertFile("password"));
		loginPage.getLoginBtn().click();
		homePage = new Home_Page(driver);
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("@AfterMethod ---------------- Logout");
		homePage.getAccountSettingsBtn().click();
		homePage.getLogOutBtn().click();
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		System.out.println("@AfterClass ---------------- Close Browser");
		Thread.sleep(3000);
		driver.quit();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("@AfterTest ---------------- ExtentReports Stoped");
		reports.flush();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("@AfterSuite ---------------- Disconnect DataBase");
	}

}
