package com.swaglabs.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglabs.pages.BaseTest;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utility.TestDataProvider;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("SwagLabs UI Tests")
@Feature("Login Page Testing")
public class LoginTest extends BaseTest {

	/**
	 * Test to verify login functionality for various users using TestNG
	 * DataProvider. Users: - Positive: standard_user, problem_user,
	 * performance_glitch_user, visual_user - Negative: locked_out_user, error_user
	 */

	@Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
	@Story("Login Feature")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Verify login functionality for different types of users")

	public void verifyLoginFunctionality(String username, String password) {
		LoginPage loginPage = new LoginPage(driver);

		// Perform login
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

		boolean isLoginSuccessful = loginPage.isLoginSuccessful();

		switch (username) {
		case "standard_user":
		case "problem_user":
		case "performance_glitch_user":
		case "visual_user":
			Assert.assertTrue(isLoginSuccessful, "[FAILED] Expected successful login for user: " + username);

			// Logout after login
			if (isLoginSuccessful) {
				HomePage homePage = new HomePage(driver);
				homePage.clickLogout();
			}
			break;

		case "locked_out_user":
		case "error_user":
			Assert.assertFalse(isLoginSuccessful, "[FAILED] Expected login failure for user: " + username);
			break;

		default:
			Assert.fail("[FAILED] Unknown username provided: " + username);
		}
	}
}
