package tests;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import configurations.ApplicationConfigurations;
import utilities.CaptureScreenShotHandler;



public class TestBase 
{
	protected WebDriver driver;

	@BeforeSuite
	@Parameters({"URL" })
	public void initSuite(@Optional("http://127.0.0.1/") String URL) 
	{
		ApplicationConfigurations.baseURl = URL;
	}

	@BeforeTest
	@Parameters({ "browser" })
	public void getDriverForTest(@Optional("chrome") String WindowBrowser)
	{
		if (WindowBrowser.equalsIgnoreCase("chrome")) {
			driver = new FirefoxDriver();
		}
		else if (WindowBrowser.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "resources"
					+ File.separator + "chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			driver = new ChromeDriver(options);
		}
		else if (WindowBrowser.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + File.separator + "resources"
					+ File.separator + "IEDriverServer.exe");
			InternetExplorerOptions options = new InternetExplorerOptions().setPageLoadStrategy(PageLoadStrategy.NONE);
			driver =  new InternetExplorerDriver(options);
		} 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to(ApplicationConfigurations.baseURl);

	}


	@AfterMethod
	public void screeshotOnFailure(ITestResult result){
		if (result.getStatus() == ITestResult.FAILURE) {
			CaptureScreenShotHandler.captureScreenshot(driver, result.getName());
		}
	}

	@AfterTest(alwaysRun=true)
	public void stopDriver() {
		driver.quit();
	}

}