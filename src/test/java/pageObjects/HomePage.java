package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import models.User;

public class HomePage extends NavigationLinks {

	@FindBy (linkText="Sign up today")
	private WebElement signUpButton;
	
	@FindBy (id="name")
	private WebElement userName;
	
	@FindBy (id="email")
	private WebElement email;
	
	@FindBy (id="signup")
	private WebElement signup;
	
	@FindBy (id="signupErrorText")
	private WebElement errorText;
	
	@FindBy (id="signupDuplicateText")
	private WebElement duplicateText;
	
	@FindBy (id="signupSuccessText")
	private WebElement successText;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public HomePage signUp(User user) {
		signUpButton.click();
		waitForJQuery();
		userName.clear();
		userName.sendKeys(user.getUsername());
		int tries = 3;
		while (!email.getText().equalsIgnoreCase(user.getEmail()) && tries > 0) {
			email.clear();
			email.sendKeys(user.getEmail());
			tries--;
		}
		signup.click();
		return this;
	}
	
	public String getResults() {
		
		if(errorText.isDisplayed())
		{
			return errorText.getText() ;
		}
		
		if(duplicateText.isDisplayed())
		{
			return duplicateText.getText() ;
		}
		
		return successText.getText() ;
	}


}
