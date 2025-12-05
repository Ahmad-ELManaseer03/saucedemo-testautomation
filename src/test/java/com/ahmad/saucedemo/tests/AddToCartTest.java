package com.ahmad.saucedemo.tests;

import com.ahmad.saucedemo.config.AppConfig;
import com.ahmad.saucedemo.pages.InventoryPage;
import com.ahmad.saucedemo.pages.LoginPage;
import com.ahmad.saucedemo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartShouldShowOneItemInCartBadge() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(AppConfig.STANDARD_USER, AppConfig.STANDARD_PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();

        int count = inventoryPage.getCartBadgeCount();

        Assert.assertEquals(count, 1, "Cart badge should show 1 item.");
    }
}
