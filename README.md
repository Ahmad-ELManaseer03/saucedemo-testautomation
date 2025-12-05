
<div align="center">

# 🧪 Saucedemo UI Test Automation Framework

A maintainable and extensible **UI test automation framework** for the [Saucedemo](https://www.saucedemo.com/) demo e-commerce application.

Built with:

![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-WebDriver-43B02A?logo=selenium&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-Testing-blueviolet)
![Maven](https://img.shields.io/badge/Maven-Build-yellow?logo=apache-maven)
![POM](https://img.shields.io/badge/Pattern-Page%20Object%20Model-informational)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?logo=mysql&logoColor=white)

</div>



## 📚 Table of Contents

1. [Objectives](#-objectives)  
2. [Tech Stack](#-tech-stack)  
3. [Architecture & Design](#-architecture--design)  
   - [Page Object Model (POM)](#31-page-object-model-pom)  
   - [BaseTest](#32-basetest)  
   - [Configuration Management](#33-configuration-management)  
   - [Data Access Layer](#34-data-access-layer)  
4. [Test Coverage](#-test-coverage)  
5. [Project Structure](#-project-structure)  
6. [Prerequisites](#-prerequisites)  
7. [Setup & Execution](#-setup--execution)  
8. [Extending the Framework](#-extending-the-framework)  
9. [Manual Test Cases Mapping](#-manual-test-cases-mapping)  
10. [Author](#-author)  



## 🎯 Objectives

- Automate and validate core user flows on Saucedemo:
  - Successful login and cart interaction.
  - Consistent product information between inventory and cart pages.
  - Checkout flow from cart to order completion.
  - Proper error handling for invalid login attempts.
- Demonstrate professional test automation practices:
  - Clean **Page Object Model (POM)** implementation.
  - Shared **BaseTest** for WebDriver lifecycle management.
  - Centralized configuration and test data.
  - Suite-based execution using **TestNG**.
  - Simple data-driven testing using **MySQL** as a customer data source.



## 🛠 Tech Stack

- **Language:** Java (JDK 11+ / 17)  
- **Build Tool:** Maven  
- **Test Framework:** TestNG  
- **UI Automation:** Selenium WebDriver  
- **Driver Management:** WebDriverManager  
- **Design Pattern:** Page Object Model (POM)  
- **Default Browser:** Microsoft Edge (configurable to Chrome / Firefox)  
- **Database:** MySQL (customer checkout data)  
- **Data Access:** JDBC (MySQL Connector/J)  



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
- Returns the number of items in the cart.  
- Navigates to the checkout flow.

#### `CheckoutStepOnePage` (`checkout-step-one.html`)

- Represents the first checkout step (customer information).  
- Fills first name, last name, and postal code fields.  
- Proceeds to the overview step.

#### `CheckoutOverviewPage`

- Represents the checkout overview page.  
- Exposes the number of items listed in the order summary.  
- Finalizes the order.

#### `CheckoutCompletePage`

- Represents the order completion page.  
- Verifies that the order completion message is displayed.

Tests never interact with raw locators directly. They use high-level methods from the Page Objects, which improves:

- **Readability**  
- **Reusability**  
- **Maintainability**



### 3.2 `BaseTest`

`com.ahmad.saucedemo.tests.base.BaseTest` centralizes WebDriver setup and teardown:

- Initializes WebDriver using **WebDriverManager**.  
- Maximizes the browser window.  
- Configures implicit waits.  
- Closes the browser and cleans up in `@AfterClass`.

All test classes **extend `BaseTest`**, which:

- Eliminates duplicated setup/teardown logic.  
- Provides a consistent WebDriver lifecycle across all tests.  



### 3.3 Configuration Management

Environment-related constants are stored in `com.ahmad.saucedemo.config.AppConfig`:

```java
public static final String BASE_URL          = "https://www.saucedemo.com/";
public static final String STANDARD_USER     = "standard_user";
public static final String STANDARD_PASSWORD = "secret_sauce";

public static final String DB_URL      = "jdbc:mysql://localhost:3306/saucedemo_test";
public static final String DB_USER     = "root";
public static final String DB_PASSWORD = "password";
```

This allows URLs, credentials, and database settings to be updated centrally without modifying test logic.

### 3.4 Data Access Layer

The data access layer provides a simple way to load checkout customer data from MySQL:

* `CheckoutUser` (in `com.ahmad.saucedemo.model`):
  Plain model class representing checkout user data (`firstName`, `lastName`, `postalCode`).

* `CheckoutUserRepository` (in `com.ahmad.saucedemo.data`):
  Uses JDBC to query the `checkout_user` table and return a `CheckoutUser` instance, which is then consumed by the checkout page objects.

This enables **data-driven checkout tests** without hardcoding customer information in the test classes.

## ✅ Test Coverage

Current automated scenarios:

1. **AddToCartTest**

   * Logs in with a valid standard user.
   * Adds the first product from the inventory to the cart.
   * Verifies that the cart badge displays `1`.

2. **CartItemNameTest**

   * Logs in with a valid standard user.
   * Adds the first product from the inventory to the cart.
   * Navigates to the cart page.
   * Verifies that the product name in the cart matches the selected product.

3. **InvalidLoginTest**

   * Opens the login page.
   * Uses a valid username with an invalid password.
   * Verifies that an error message is displayed and the user remains on the login page.

4. **CheckoutFromDatabaseTest.checkoutWithProduct_usesCustomerFromDatabase**

   * Logs in with a valid user.
   * Adds the first product from the inventory to the cart.
   * Loads checkout customer data from **MySQL**.
   * Completes the checkout flow using the `checkout-step-one`, overview, and completion pages.
   * Verifies that the order completion message is displayed.

5. **CheckoutFromDatabaseTest.checkoutWithEmptyCart_usesCustomerFromDatabase**

   * Logs in with a valid user.
   * Navigates to the cart page without adding any products.
   * Loads checkout customer data from **MySQL**.
   * Proceeds through the checkout flow with an empty cart.
   * Verifies behavior on the overview page (no items present) and order completion page.

Each automated test maps directly to a documented manual test case (see [Manual Test Cases Mapping](#-manual-test-cases-mapping)).

## 🗂 Project Structure

```text
saucedemo-automation/
 ├─ pom.xml                       # Maven configuration (dependencies, plugins)
 ├─ testng.xml                    # TestNG suite configuration
 ├─ src
 │  ├─ main
 │  │  └─ java
 │  │     └─ com.ahmad.saucedemo
 │  │         ├─ app
 │  │         │   └─ SauceDemoSimulator.java   # Optional console/OOP demo
 │  │         ├─ config
 │  │         │   └─ AppConfig.java            # Central configuration (URL, credentials, DB)
 │  │         ├─ data
 │  │         │   └─ CheckoutUserRepository.java
 │  │         ├─ model
 │  │         │   ├─ Cart.java
 │  │         │   ├─ Product.java
 │  │         │   └─ CheckoutUser.java
 │  │         ├─ pages                          # Page Object classes
 │  │         │   ├─ LoginPage.java
 │  │         │   ├─ InventoryPage.java
 │  │         │   ├─ CartPage.java
 │  │         │   ├─ CheckoutStepOnePage.java
 │  │         │   ├─ CheckoutOverviewPage.java
 │  │         │   └─ CheckoutCompletePage.java
 │  │         └─ service
 │  │             └─ InventoryService.java      # Optional business-logic demo
 │  └─ test
 │     └─ java
 │         └─ com.ahmad.saucedemo.tests
 │             ├─ base
 │             │   └─ BaseTest.java             # Shared WebDriver setup/teardown
 │             ├─ AddToCartTest.java            # TC-001
 │             ├─ CartItemNameTest.java         # TC-002
 │             ├─ InvalidLoginTest.java         # TC-003
 │             └─ CheckoutFromDatabaseTest.java # TC-004, TC-005
```

## 📦 Prerequisites

* **JDK:** 11 or 17 installed and configured in `JAVA_HOME`.
* **Maven:** Installed and available on system `PATH`.
* **IDE:** Eclipse, IntelliJ IDEA, or VS Code with Java support.
* **Browser:** Microsoft Edge installed (default WebDriver browser).
* **Database:** MySQL instance available (local or remote) with a `saucedemo_test` database.

All third-party dependencies are managed via **Maven** in `pom.xml`.

## ▶️ Setup & Execution

### 7.1 Importing the Project

1. Clone or download this repository.

2. Open it in the IDE as a Maven project:

   * **Eclipse:** `File → Import → Existing Maven Projects`
   * **IntelliJ IDEA:** `Open → Select pom.xml`

3. Wait for Maven to resolve all dependencies.

### 7.2 Database Setup

Create the database and table used by the checkout tests (example):

```sql
CREATE DATABASE saucedemo_test;
USE saucedemo_test;

CREATE TABLE checkout_user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    first_name  VARCHAR(50) NOT NULL,
    last_name   VARCHAR(50) NOT NULL,
    postal_code VARCHAR(20) NOT NULL
);

INSERT INTO checkout_user (first_name, last_name, postal_code) VALUES
('John',  'Doe',      '12345'),
('Ahmad', 'Manaseer', '11937');
```

Update `DB_URL`, `DB_USER`, and `DB_PASSWORD` in `AppConfig` if needed.

### 7.3 Running Tests from the IDE

* **Run a single test class** (e.g. `AddToCartTest`):

  * Right-click the class → `Run As → TestNG Test`.

* **Run the entire suite**:

  * Right-click `testng.xml` → `Run As → TestNG Suite`.

### 7.4 Running Tests from Maven

From the project root:

```bash
mvn test
```

## 🔄 Extending the Framework

To add a new UI test scenario:

1. Create or extend a **Page Object** under `com.ahmad.saucedemo.pages`.
2. Add high-level methods for the required interactions (sorting, removing items, more checkout cases, etc.).
3. Create a new test class under `com.ahmad.saucedemo.tests` extending `BaseTest`.
4. Optionally register the new test class in `testng.xml` to include it in the suite.

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

## 🧾 Manual Test Cases Mapping

The framework currently automates the following manual test cases:

| Test Case ID | Description                                                                                 | Automated Class / Method                                                    |
| ------------ | ------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------- |
| **TC-001**   | Add first product to cart – cart badge shows `1`                                            | `AddToCartTest.addToCartShouldShowOneItemInCartBadge()`                     |
| **TC-002**   | Product name in cart matches the selected product                                           | `CartItemNameTest.addedItemShouldAppearInCartWithSameName()`                |
| **TC-003**   | Invalid login displays an error message on the login page                                   | `InvalidLoginTest.invalidLoginShouldShowErrorMessage()`                     |
| **TC-004**   | Checkout with items in cart using customer data loaded from MySQL                           | `CheckoutFromDatabaseTest.checkoutWithProduct_usesCustomerFromDatabase()`   |
| **TC-005**   | Checkout with an empty cart using customer data loaded from MySQL and verify empty overview | `CheckoutFromDatabaseTest.checkoutWithEmptyCart_usesCustomerFromDatabase()` |

## 👤 Author

<div align="center">
  <table>
    <tr>
      <td align="center">
        <strong>Ahmad Ghazi El-Manaseer</strong><br/>
        QA / Test Automation Engineer (Java + Selenium + TestNG)<br/><br/>
        <a href="https://www.linkedin.com/in/ahmad-elmanaseer03">
          <img src="https://img.shields.io/badge/LinkedIn-View%20Profile-0A66C2?logo=linkedin&logoColor=white" alt="LinkedIn Profile"/>
        </a>
        <a href="https://github.com/Ahmad-ELManaseer03">
          <img src="https://img.shields.io/badge/GitHub-Ahmad--ELManaseer03-181717?logo=github&logoColor=white" alt="GitHub Profile"/>
        </a>
      </td>
    </tr>
  </table>
</div>

This project is part of a professional QA automation portfolio and demonstrates the ability to design, implement, and maintain UI test automation frameworks following industry best practices.

```
::contentReference[oaicite:0]{index=0}
```
