package com.ahmad.saucedemo.pages;

import com.ahmad.saucedemo.config.AppConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage {

    private WebDriver driver;

    // عناصر الصفحة
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton   = By.id("login-button");

    // عنصر رسالة الخطأ
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(AppConfig.BASE_URL);
    }

    public void login(String username, String password) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    // ترجع نص رسالة الخطأ، أو String فاضي لو ما في رسالة
    public String getErrorMessageText() {
        try {
            return driver.findElement(errorMessage).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
