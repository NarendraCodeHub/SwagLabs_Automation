package com.swaglabs.testcases;

import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
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

        // Validate login success or failure
        if(username.equals("standard_user")) {
            Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed for standard_user");
        } else {
            Assert.assertTrue(loginPage.isLoginUnsuccessful(), "Login should have failed for " + username);
        }
    }
}
