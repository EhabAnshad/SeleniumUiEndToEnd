package tests.users;

import org.testng.Assert;
import org.testng.annotations.Test;

import helpers.GenerateUser;
import models.User;
import pageObjects.HomePage;
import tests.TestBase;

public class AddExistingUser extends TestBase {

    @Test 
    public void AddExistingUserTest() {
    	//setup
    	User testUser = new GenerateUser();
    	HomePage homePageObject = new HomePage(driver);
    	
    	String result =  homePageObject.signUp(testUser).signUp(testUser).getResults();
    	
    	Assert.assertTrue(result
    			.equalsIgnoreCase("Email already exists, please try again later."),
    			result);
    	
    	//clean up
    	homePageObject.openUsers().deleteUser(testUser);
    }
}
