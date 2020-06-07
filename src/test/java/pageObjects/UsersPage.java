package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import models.User;

public class UsersPage extends NavigationLinks {
	
	@FindBy (name="name")
	private List<WebElement> userNames;
	
	@FindBy (name="email")
	private List<WebElement> emails;
	
	@FindBy (id="user_name")
	private WebElement userName;
	
	@FindBy (id="update")
	private WebElement updateButton;
	
	@FindBy (id="delete")
	private WebElement deleteButton;
			
	public UsersPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean doesUserExists(User user) {
		return emails.stream()
		.filter(s-> s.getText().equalsIgnoreCase(user.getEmail()))
		.count() > 0;
	}

	public UsersPage editUser(User oldUser, User updatedUser) {
		WebElement editButton = driver.findElement(By.cssSelector("a[data-user_name='" + oldUser.getUsername() + "']"));
		editButton.click();
		waitForJQuery();
		userName.clear();
		userName.sendKeys(updatedUser.getUsername());
		updateButton.click();
		return this;
	}

	public String getUserName(User testUser) {
		WebElement user = driver.findElement(By.id(buildUserSelector(testUser)));
		return user.getText();
	}
		
	public UsersPage deleteUser(User testUser) {
		WebElement deleteUserButton = driver.findElement(By.cssSelector("a[name='" +testUser.getEmail() + "_delete']"));
		deleteUserButton.click();
		fluentWait(By.id("delete"));
		deleteButton.click();
		return this;
	}
	
	private String buildUserSelector(User testUser) {
		return testUser.getEmail() + "_" + testUser.getUsername();
	}



}
