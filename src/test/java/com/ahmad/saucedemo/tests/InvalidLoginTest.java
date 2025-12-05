package com.ahmad.saucedemo.tests;

import com.ahmad.saucedemo.config.AppConfig;
import com.ahmad.saucedemo.pages.LoginPage;
import com.ahmad.saucedemo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {

    @Test
    public void invalidLoginShouldShowErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        loginPage.login(AppConfig.STANDARD_USER, "wrong_password");
        String errorText = loginPage.getErrorMessageText();
        System.out.println("Error message: " + errorText);
        Assert.assertFalse(errorText.isEmpty(), "Error message should be displayed for invalid login.");

    }
}
