package com.swaglabs.utility;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][]{
            {"standard_user", "secret_sauce"},
            {"locked_out_user", "secret_sauce"},
            {"problem_user","secret_sauce"},
            {"performance_glitch_user","secret_sauce"},
            {"error_user","secret_sauce"},
            {"visual_user","secret_sauce"}
        };
    }
}
