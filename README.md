<div align="center">

# 🧪 Saucedemo UI Test Automation Framework

A maintainable and extensible **UI test automation framework** for the [Saucedemo](https://www.saucedemo.com/) demo e-commerce application.

Built with:

![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-43B02A?logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-Testing-blueviolet)
![Maven](https://img.shields.io/badge/Maven-Build-yellow?logo=apache-maven)
![POM](https://img.shields.io/badge/Pattern-Page%20Object%20Model-informational)

</div>

---

## 📚 Table of Contents

1. [Objectives](#-objectives)  
2. [Tech Stack](#-tech-stack)  
3. [Architecture & Design](#-architecture--design)  
   - [Page Object Model (POM)](#31-page-object-model-pom)  
   - [BaseTest](#32-basetest)  
   - [Configuration Management](#33-configuration-management)  
4. [Test Coverage](#-test-coverage)  
5. [Project Structure](#-project-structure)  
6. [Prerequisites](#-prerequisites)  
7. [Setup & Execution](#-setup--execution)  
8. [Extending the Framework](#-extending-the-framework)  
9. [Manual Test Cases Mapping](#-manual-test-cases-mapping)  
10. [Author](#-author)  

---

## 🎯 Objectives

- Automate and validate core user flows on Saucedemo:
  - Successful login and cart interaction.
  - Consistent product information between inventory and cart pages.
  - Proper error handling for invalid login attempts.
- Demonstrate professional test automation practices:
  - Clean **Page Object Model (POM)** implementation.
  - Shared **BaseTest** for WebDriver lifecycle management.
  - Centralized configuration and test data.
  - Suite-based execution using **TestNG**.

---

## 🛠 Tech Stack

- **Language:** Java (JDK 11+ / 17)  
- **Build Tool:** Maven  
- **Test Framework:** TestNG  
- **UI Automation:** Selenium WebDriver  
- **Driver Management:** WebDriverManager  
- **Design Pattern:** Page Object Model (POM)  
- **Default Browser:** Microsoft Edge (can be adjusted to Chrome / Firefox)  

All external dependencies are declared and managed in `pom.xml`.

---

## 🧱 Architecture & Design

### 3.1 Page Object Model (POM)

All UI interactions are encapsulated in dedicated **Page Object** classes under `com.ahmad.saucedemo.pages`:

#### `LoginPage`

- Opens the Saucedemo login page.  
- Performs login with supplied credentials.  
- Exposes error messages for invalid login scenarios.

#### `InventoryPage`

- Represents the product inventory page.  
- Adds products to the shopping cart.  
- Reads the cart badge counter.  
- Navigates to the cart page.

#### `CartPage`

- Represents the shopping cart page.  
- Reads product information (e.g. product name) from the cart.

The tests never interact with raw locators directly. They use high-level methods from the Page Objects, which improves:

- **Readability**  
- **Reusability**  
- **Maintainability**

---

### 3.2 `BaseTest`

`com.ahmad.saucedemo.tests.base.BaseTest` centralizes WebDriver setup and teardown:

- Initializes WebDriver using **WebDriverManager**.  
- Maximizes the browser window.  
- Configures implicit waits.  
- Closes the browser and cleans up in `@AfterClass`.

All test classes **extend `BaseTest`**, which:

- Eliminates duplicated setup/teardown logic.  
- Provides a consistent WebDriver lifecycle across all tests.  

---

### 3.3 Configuration Management

Environment-related constants are stored in `com.ahmad.saucedemo.config.AppConfig`:

```java
public static final String BASE_URL          = "https://www.saucedemo.com/";
public static final String STANDARD_USER     = "standard_user";
public static final String STANDARD_PASSWORD = "secret_sauce";