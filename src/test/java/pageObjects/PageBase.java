package pageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.google.common.base.Function;

import configurations.ApplicationConfigurations;


/**
 * This Class is a Page Base for the other Classes.
 */

public class PageBase {


	protected WebDriver driver;

	public PageBase(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
		this.driver = driver;
	}

	public WebElement fluentWait(final By selector , WebDriver driver)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(ApplicationConfigurations.driverDefaultFluentWait))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver)
			{
				return driver.findElement(selector);
			}
		});
	}
	
	public void waitForPageToLoad()
	{
	   new WebDriverWait(driver, 30)
	   		.until((ExpectedCondition<Boolean>) 
				   wd ->((JavascriptExecutor) wd)
				   .executeScript("return document.readyState")
				   .equals("complete"));
	}

	public void waitForJQuery(WebDriver driver) 
	{
		new WebDriverWait(driver, 10)
				.until((ExpectedCondition<Boolean>) 
						wd ->((JavascriptExecutor) wd)
						.executeScript("return !!window.jQuery && window.jQuery.active")
								   .equals("0"));
	}
	
	public void cleanAndSendKeys(WebElement myElement, String keysToSend)
	{
		myElement.clear();
		myElement.sendKeys(keysToSend);
		
	}
	
	public String getElementValue(WebElement element)
	{
		return element.getAttribute("Value");
	}
	
	public String getCurrentPageTitle()
	{
		return driver.getCurrentUrl();
	}


}