package com.ahmad.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    // كل أزرار Add to cart
    private By addToCartButtons = By.cssSelector("button.btn_inventory");

    // البادج فوق أيقونة السلة
    private By cartBadge = By.className("shopping_cart_badge");

    // كل المنتجات في صفحة الإنفنتوري
    private By inventoryItems = By.className("inventory_item");

    // لينك / أيقونة السلة
    private By cartLink = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // تستخدمها AddToCartTest
    public void addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    // تستخدمها CartItemNameTest
    public String addFirstProductToCartAndGetName() {
        List<WebElement> items = driver.findElements(inventoryItems);
        if (items.isEmpty()) {
            throw new IllegalStateException("No products found on inventory page");
        }

        WebElement firstItem = items.get(0);

        // جلب اسم المنتج
        String name = firstItem.findElement(By.className("inventory_item_name")).getText();

        // زر Add to cart لنفس المنتج
        WebElement addButton = firstItem.findElement(By.cssSelector("button.btn_inventory"));
        addButton.click();

        return name;
    }

    // تستخدمها AddToCartTest
    public int getCartBadgeCount() {
        WebElement badge = driver.findElement(cartBadge);
        String text = badge.getText();
        return Integer.parseInt(text);
    }

    // تستخدمها CartItemNameTest
    public void goToCart() {
        driver.findElement(cartLink).click();
    }
}
