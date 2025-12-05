package com.ahmad.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {

    private WebDriver driver;
    private By addToCartButtons = By.cssSelector("button.btn_inventory");
    private By cartBadge = By.className("shopping_cart_badge");
    private By inventoryItems = By.className("inventory_item");
    private By cartLink = By.className("shopping_cart_link");
    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public String addFirstProductToCartAndGetName() {
        List<WebElement> items = driver.findElements(inventoryItems);
        if (items.isEmpty()) {
            throw new IllegalStateException("No products found on inventory page");
        }

        WebElement firstItem = items.get(0);

        String name = firstItem.findElement(By.className("inventory_item_name")).getText();

        WebElement addButton = firstItem.findElement(By.cssSelector("button.btn_inventory"));
        addButton.click();

        return name;
    }

    public int getCartBadgeCount() {
        WebElement badge = driver.findElement(cartBadge);
        String text = badge.getText();
        return Integer.parseInt(text);
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }
}
