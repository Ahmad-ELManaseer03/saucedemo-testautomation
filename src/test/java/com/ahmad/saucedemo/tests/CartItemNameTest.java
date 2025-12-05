package com.ahmad.saucedemo.tests;

import com.ahmad.saucedemo.config.AppConfig;
import com.ahmad.saucedemo.pages.CartPage;
import com.ahmad.saucedemo.pages.InventoryPage;
import com.ahmad.saucedemo.pages.LoginPage;
import com.ahmad.saucedemo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartItemNameTest extends BaseTest {

    @Test
    public void addedItemShouldAppearInCartWithSameName() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(AppConfig.STANDARD_USER, AppConfig.STANDARD_PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        String expectedName = inventoryPage.addFirstProductToCartAndGetName();

        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        String actualName = cartPage.getFirstItemName();

        Assert.assertEquals(actualName, expectedName,
                "Item name in cart should match selected product.");
    }
}
