package com.ahmad.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {

    private final WebDriver driver;

    private final By completeHeader = By.cssSelector("[data-test='complete-header']");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderCompleteMessageDisplayed() {
        return driver.findElement(completeHeader).isDisplayed();
    }

    public String getOrderCompleteText() {
        return driver.findElement(completeHeader).getText();
    }
}
