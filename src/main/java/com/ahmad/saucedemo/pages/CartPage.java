package com.ahmad.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private final WebDriver driver;

    private final By firstItemName = By.cssSelector(".cart_item .inventory_item_name");
    private final By cartItems = By.cssSelector(".cart_item");
    private final By checkoutButton = By.cssSelector("[data-test='checkout']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstItemName() {
        return driver.findElement(firstItemName).getText();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
