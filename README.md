
<div align="center">

# 🧪 Saucedemo UI Test Automation Framework

A maintainable and extensible **UI test automation framework** for the [Saucedemo](https://www.saucedemo.com/) demo e-commerce website.

Built with:

![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=coffeescript&logoColor=white)
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

- Validate core user flows on Saucedemo:
  - Successful login and cart interaction.
  - Correct product information between pages.
  - Proper error handling for invalid login attempts.
- Demonstrate professional automation engineering practices:
  - Page Object Model (POM).
  - Base test class for WebDriver lifecycle management.
  - Centralized configuration.
  - TestNG suite-based execution.

---

## 🛠 Tech Stack

- **Language:** Java (JDK 11+ / 17)  
- **Build Tool:** Maven  
- **Test Framework:** TestNG  
- **UI Automation:** Selenium WebDriver  
- **Driver Management:** WebDriverManager  
- **Design Pattern:** Page Object Model (POM)  
- **Browser:** Microsoft Edge (configurable to Chrome/Firefox)  

---

## 🧱 Architecture & Design

### 3.1 Page Object Model (POM)

All UI interactions are encapsulated in dedicated **Page Object** classes under `com.ahmad.saucedemo.pages`:

#### `LoginPage`

- Opens the login page.  
- Performs login with supplied credentials.  
- Reads and exposes error messages for invalid login scenarios.

#### `InventoryPage`

- Represents the product inventory page.  
- Adds products to the shopping cart.  
- Reads the cart badge counter.  
- Navigates to the cart page.

#### `CartPage`

- Represents the shopping cart page.  
- Reads product information (e.g. product name) from the cart.

Tests never access raw locators directly. They consume high-level methods from the Page Objects, which improves:

- **Readability**
- **Reusability**
- **Maintainability**

---

### 3.2 `BaseTest`

`com.ahmad.saucedemo.tests.base.BaseTest` centralizes WebDriver setup and teardown:

- Initializes WebDriver using **WebDriverManager**.  
- Maximizes the browser window.  
- Configures implicit waits.  
- Ensures browser cleanup in `@AfterClass`.

All test classes **extend `BaseTest`**, which:

- Avoids duplicated setup/teardown code.  
- Provides a consistent WebDriver lifecycle across all tests.

---

### 3.3 Configuration Management

Environment-related constants are stored in `com.ahmad.saucedemo.config.AppConfig`:

```java
public static final String BASE_URL = "https://www.saucedemo.com/";
public static final String STANDARD_USER = "standard_user";
public static final String STANDARD_PASSWORD = "secret_sauce";
````

This allows URLs and credentials to be updated centrally without modifying test logic.

---

## ✅ Test Coverage

Current automated scenarios:

1. **AddToCartTest**

   * Logs in with a valid user.
   * Adds the first product from the inventory to the cart.
   * Verifies that the cart badge displays `1`.

2. **CartItemNameTest**

   * Logs in with a valid user.
   * Adds the first product from the inventory to the cart.
   * Navigates to the cart page.
   * Verifies that the product name in the cart matches the selected product.

3. **InvalidLoginTest**

   * Opens the login page.
   * Uses a valid username with an invalid password.
   * Verifies that an error message is displayed and the user stays on the login page.

Each automated test maps directly to a documented manual test case (see [Manual Test Cases Mapping](#-manual-test-cases-mapping)).

---

## 🗂 Project Structure

```text
saucedemo-automation/
 ├─ pom.xml                  # Maven configuration (dependencies, plugins)
 ├─ testng.xml               # TestNG suite configuration
 ├─ src
 │  ├─ main
 │  │  └─ java
 │  │     └─ com.ahmad.saucedemo
 │  │         ├─ config
 │  │         │   └─ AppConfig.java         # Central configuration (URL, credentials)
 │  │         ├─ pages                      # Page Object classes
 │  │         │   ├─ LoginPage.java
 │  │         │   ├─ InventoryPage.java
 │  │         │   └─ CartPage.java
 │  │         └─ (optional console/OOP demo packages)
 │  └─ test
 │     └─ java
 │         └─ com.ahmad.saucedemo.tests
 │             ├─ base
 │             │   └─ BaseTest.java         # Shared WebDriver setup/teardown
 │             ├─ AddToCartTest.java        # TC-001
 │             ├─ CartItemNameTest.java     # TC-002
 │             └─ InvalidLoginTest.java     # TC-003
```

---

## 📦 Prerequisites

* **JDK:** 11 or 17 installed and configured in `JAVA_HOME`.
* **Maven:** Installed and available on system `PATH` (for CLI usage).
* **IDE:** Eclipse / IntelliJ IDEA / VS Code with Java support.
* **Browser:** Microsoft Edge installed (default WebDriver browser).

All third-party dependencies are managed via **Maven** in `pom.xml`.

---

## ▶️ Setup & Execution

### 7.1 Importing the Project

1. Clone or download the repository.
2. Open it in the IDE as a Maven project:

   * **Eclipse:** `File → Import → Existing Maven Projects`
   * **IntelliJ IDEA:** `Open → Select pom.xml`

Wait until Maven completes dependency resolution.

---

### 7.2 Running Tests from IDE

* To run a **single test class** (e.g. `AddToCartTest`):

  * Right-click the class → `Run As → TestNG Test`.

* To run the **entire suite**:

  * Right-click `testng.xml` → `Run As → TestNG Suite`.

---

### 7.3 Running Tests from Maven

From the project root:

```bash
mvn test
```

---

## 🔄 Extending the Framework

To add a new UI test scenario:

1. Create or extend a **Page Object** under `com.ahmad.saucedemo.pages`.
2. Add high-level methods for the required interactions (e.g. sorting, removing items, checkout).
3. Create a new test class under `com.ahmad.saucedemo.tests` extending `BaseTest`.
4. Optionally, register the test class in `testng.xml` to include it in the suite.

Example skeleton for a new test:

```java
public class CheckoutTest extends BaseTest {

    @Test
    public void userCanCompleteCheckout() {
        // Arrange: login and add items via POM classes
        // Act: navigate through checkout steps
        // Assert: verify order completion message
    }
}
```

---

## 🧾 Manual Test Cases Mapping

The framework currently automates the following manual test cases:

* **TC-001 – Add first product to cart – badge shows 1**

  * Automated in `AddToCartTest`.

* **TC-002 – Added product name in cart matches selected product**

  * Automated in `CartItemNameTest`.

* **TC-003 – Invalid login shows an error message**

  * Automated in `InvalidLoginTest`.

---

## 👤 Author

**Name:** Ahmad Ghazi El-Manaseer
**Role:** QA / Test Automation Engineer (Java + Selenium + TestNG)

This project is part of a professional QA automation portfolio and demonstrates the ability to design, implement, and maintain UI test automation frameworks following industry best practices.

```


