package com.ahmad.saucedemo.tests;

import com.ahmad.saucedemo.config.AppConfig;
import com.ahmad.saucedemo.data.CheckoutUserRepository;
import com.ahmad.saucedemo.model.CheckoutUser;
import com.ahmad.saucedemo.pages.*;
import com.ahmad.saucedemo.tests.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutFromDatabaseTest extends BaseTest {

    private final CheckoutUserRepository userRepository = new CheckoutUserRepository();

    @Test
    public void checkoutWithProduct_usesCustomerFromDatabase() {
        CheckoutUser customer = userRepository.getUserById(1);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(AppConfig.STANDARD_USER, AppConfig.STANDARD_PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addFirstProductToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "Cart should contain 1 item before checkout.");

        cartPage.clickCheckout();

        CheckoutStepOnePage stepOnePage = new CheckoutStepOnePage(driver);
        stepOnePage.fillCustomerInfo(customer);
        stepOnePage.clickContinue();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        Assert.assertTrue(overviewPage.getCartItemsCount() > 0,
                "Overview should list at least one item.");

        overviewPage.clickFinish();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(completePage.isOrderCompleteMessageDisplayed(),
                "Order completion message should be displayed.");
    }

    @Test
    public void checkoutWithEmptyCart_usesCustomerFromDatabase() {
        CheckoutUser customer = userRepository.getUserById(1);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(AppConfig.STANDARD_USER, AppConfig.STANDARD_PASSWORD);

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemCount(), 0,
                "Cart should be empty before checkout.");

        cartPage.clickCheckout();

        CheckoutStepOnePage stepOnePage = new CheckoutStepOnePage(driver);
        stepOnePage.fillCustomerInfo(customer);
        stepOnePage.clickContinue();

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        Assert.assertEquals(overviewPage.getCartItemsCount(), 0,
                "Overview should show no items when checking out with an empty cart.");

        overviewPage.clickFinish();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(completePage.isOrderCompleteMessageDisplayed(),
                "Order completion message should be displayed.");
    }
}
