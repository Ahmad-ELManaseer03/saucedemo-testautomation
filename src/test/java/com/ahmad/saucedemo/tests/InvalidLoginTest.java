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

        // 1) افتح صفحة اللوجن
        loginPage.open();

        // 2) جرّب باسورد غلط (يوزر صحيح + باسورد غلط)
        loginPage.login(AppConfig.STANDARD_USER, "wrong_password");

        // 3) اقرأ نص رسالة الخطأ
        String errorText = loginPage.getErrorMessageText();
        System.out.println("Error message: " + errorText);

        // 4) تأكد إنها مش فاضية (يعني في رسالة ظهرت فعلاً)
        Assert.assertFalse(errorText.isEmpty(), "Error message should be displayed for invalid login.");

        // لو حاب تتأكد أكثر، ممكن تضيف:
        // Assert.assertTrue(errorText.toLowerCase().contains("username and password"),
        //         "Error message should mention username and password.");
    }
}
