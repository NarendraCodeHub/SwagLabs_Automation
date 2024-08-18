package com.swaglabs.testcases;

import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utility.TestDataProvider;

import org.testng.Assert;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void loginTest(String username, String password) {
        // Initialize the LoginPage object
        LoginPage loginPage = new LoginPage(driver);

        // Perform the login action
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        
        
     // Verify if the login is successful or not based on the Username
        boolean isLoginSuccessful = loginPage.isLoginSuccessful();
        
        switch (username) {
            case "standard_user":
            case "problem_user":
            case "performance_glitch_user":
            case "visual_user":
                // These users should be able to log in successfully
                Assert.assertTrue(isLoginSuccessful, "Login failed for user: " + username);
                
                // If login is successful, perform logout
                if (isLoginSuccessful) {
                    HomePage homePage = new HomePage(driver);
                    homePage.logout();
                }
                break;
                
            case "locked_out_user":
            case "error_user":
                // These users should not be able to log in
                Assert.assertFalse(isLoginSuccessful, "Login was successful but shouldn't be for user: " + username);
                break;
                
            default:
                Assert.fail("Unknown username: " + username);
        }
    }
}
