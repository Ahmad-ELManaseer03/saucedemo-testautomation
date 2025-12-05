package com.ahmad.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    // اسم المنتجات في صفحة الكارت
    private By cartItemName = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // إرجاع اسم أول منتج موجود بالسلة
    public String getFirstItemName() {
        return driver.findElement(cartItemName).getText();
    }
}
