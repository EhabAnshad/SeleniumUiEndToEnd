package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import models.User;

public class HomePage extends PageBase {

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
	
	public HomePage SignUp(User user) {
		signUpButton.click();
		waitForJQuery();
		userName.sendKeys(user.getUsername());
		email.sendKeys(user.getEmail());
		signup.click();
		return this;
	}
	
	public String GetResults() {
		boolean isError= errorText.getAttribute("style").equalsIgnoreCase("display:none");
		
		if(isError)
		{
			return errorText.getText() ;
		}
		
		boolean isDuplicate= duplicateText.getAttribute("style").equalsIgnoreCase("display:none");
		
		if(isDuplicate)
		{
			return duplicateText.getText() ;
		}
		
		return successText.getText() ;
	}

}
