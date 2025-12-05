package com.ahmad.saucedemo.pages;

import com.ahmad.saucedemo.model.CheckoutUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage {

    private final WebDriver driver;

    private final By firstNameInput = By.cssSelector("[data-test='firstName']");
    private final By lastNameInput = By.cssSelector("[data-test='lastName']");
    private final By postalCodeInput = By.cssSelector("[data-test='postalCode']");
    private final By continueButton = By.cssSelector("[data-test='continue']");

    public CheckoutStepOnePage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillCustomerInfo(CheckoutUser user) {
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(user.getFirstName());

        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(user.getLastName());

        driver.findElement(postalCodeInput).clear();
        driver.findElement(postalCodeInput).sendKeys(user.getPostalCode());
    }

    public void clickContinue() {
        driver.findElement(continueButton).click();
    }
}
