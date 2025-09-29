# 🚀 DemoQA UI Automation Test Suite (Selenium + TestNG + Maven)

## 📌 Senior QA Automation – Coding Assignment

This project is a **robust UI automation framework** designed for the [DemoQA](https://demoqa.com/) application.  
It was built using **Java, Selenium WebDriver, TestNG, Maven**, and follows **Page Object Model (POM)**.  

The framework covers **critical user journeys**, leverages **JSON fixtures for test data**, supports **parallel execution**, generates **HTML reports**, and captures **screenshots on test failures**.

## 📘 Overview  
This project is a **UI Test Automation Framework** built using:  
- Selenium WebDriver (UI automation)  
- Java (programming language)  
- TestNG (test runner & assertions)  
- Maven (build tool & dependency management)  
- Extent Reports / Allure Reports (reporting)
- Screenshots on test failures

Designed to automate the **DemoQA website** and is fully **CI/CD pipeline ready** (via GitHub Actions).  

---

## 🔹 Features  
- ✅ Page Object Model (POM) design pattern  
- ✅ Centralized utilities for waits, drivers, configs  
- ✅ Cross-browser support (Chrome, Firefox)  
- ✅ Test data management -Fixtures (properties/JSON)  
- ✅ HTML reporting (Extent / Allure integration)  , SureFire Plugin
- ✅ CI/CD ready (GitHub Actions pipeline configured)  

---

## 🎯 Objectives & Requirements (Covered)

### ✅ Functional Scenarios
1. **Login Page Automation**
   - Verify login with valid credentials → navigates to dashboard/profile.
   - Verify login with invalid credentials → shows error message.
   - Verify required field validations for blank username & password.

2. **Practice Form Automation**
   - Navigate to: `Forms > Practice Form`.
   - Fill out form with **test data from JSON**.
   - Upload a sample file (`testfile.png`).
   - Select date of birth, gender, hobbies, etc.
   - Submit form and validate:
     - Confirmation modal is displayed.
     - Submitted data matches entered data.

3. **Web Tables Automation**
   - Navigate to: `Elements > Web Tables`.
   - Add a new user (data-driven from JSON).
   - Edit the user’s **Age** field and verify updated value.
   - Delete a user and validate removal.
   - Confirm all UI updates reflect correctly.

4. **Checkbox Interaction**
   - Navigate to: `Elements > Check Box`.
   - Expand all nodes in tree structure.
   - Select multiple checkboxes.
   - Validate selected values appear in output.


5.**Profile Page - LoginDataDrivenTests**
   **Authentication workflows (Login/Profile)**
**Data-Driven Login (from Excel/CSV/TestNG DataProvider):**

**Multiple sets of valid and invalid credentials tested.**
-Valid Login → User logs in with correct credentials, lands on Profile page.

-Invalid Login → Wrong username/password shows error message.

-Required Field Validation → Blank fields trigger mandatory field errors.

---

### **Summary **

**This project covers:**

**Authentication workflows (Login/Profile)**
**Data-Driven Login (from Excel/CSV/TestNG DataProvider):**

**Multiple sets of valid and invalid credentials tested.**

Form handling (Practice Form)

CRUD operations on Web Tables

Checkbox tree validation

Reusable automation framework (POM + TestNG + ExtentReports + Parallel execution)

### ✅ Technical Requirements
- **POM (Page Object Model)** implemented.
- **Reusable utilities**: DriverFactory, WaitUtils, TestListener.
- **JSON test data** used (`loginData.json`, `webTablesData.json`).
- **Assertions** at every validation step.
- **Explicit wait strategies** (no flaky sleeps).
- **Cross-browser execution** (Chrome, Firefox).
- **Parallel execution** via TestNG XML.
- **Screenshots on failure** stored automatically.
- **HTML reporting** via Extent Reports.
- **Automatic WebDriver setup** via WebDriverManager

---

### 🎁 Bonus Implementations
- ✅ Data-driven testing (Login scenario) using **TestNG DataProvider** + JSON.  
- ✅ Retry mechanism in **DriverFactory** for session start failures.  
- ✅ **Parallel execution** enabled (configurable in `testng.xml`).  
- ✅ **Extent HTML Report** generated in `test-output/`.  
- ✅ **Screenshots on failures** → `test-output/screenshots/`.  
- ✅ Modular project structure → easy to scale.  
- 🔜 CI/CD ready.  

---

## 📂 Project Structure

```bash
demoqa-ui-automation-selenium-java-full
│── pom.xml                        # Maven dependencies
│── README.md                      # Project documentation
│
├── src
│   ├── main
│   │   └── java/com/demoqa
│   │       ├── base/
│   │       │   └── BasePage.java
│   │       ├── pages/             # Page Objects
│   │       │   ├── LoginPage.java
│   │       │   ├── ProfilePage.java
│   │       │   ├── PracticeFormPage.java
│   │       │   ├── WebTablesPage.java
│   │       │   └── CheckBoxPage.java
│   │       └── utils/
│   │           ├── DriverFactory.java
│   │           ├── WaitUtils.java
│   │           └── TestListener.java
│   │
│   ├── test
│   │   └── java/com/demoqa/tests/
│   │       ├── BaseTest.java
│   │       ├── LoginTests.java
│   │       ├── LoginDataDrivenTests.java
│   │       ├── PracticeFormTests.java
│   │       ├── WebTablesTests.java
│   │       └── CheckBoxTests.java
│   │
│   └── resources/
│       ├── testdata/
│       │   ├── loginData.json
│       │   ├── webTablesData.json
│       ├── testfile.png           # Sample upload file
│       └── testng.xml             # TestNG suite config
│
└── test-output/
    ├── index.html                 # Extent HTML Report
    ├── screenshots/               # Screenshots captured on failure
    └── logs/                      # Optional log files


## Run
```bash
mvn clean test -Dbrowser=chrome
# or
mvn clean test -Dbrowser=firefox
```

**Reports:**
- Extent: `test-output/ExtentReports/index.html`
- Screenshots on failure: `test-output/ExtentReports/screenshots/`
- Surefire: `target/surefire-reports/`


**Reports & Outputs**

HTML Report → test-output/index.html

Screenshots on failure → test-output/screenshots/

Logs (optional) → test-output/logs/


**⚠️ Known Limitations**


Only Chrome & Firefox configured (Edge/Safari can be added).


**🏆 Evaluation Criteria Mapping**
Criteria	Implementation
Test Coverage	Login, Forms, WebTables, Checkboxes covered.
Code Structure	POM, utilities, modular, maintainable.
Robustness	Explicit waits, retries, exception handling.
Reporting	Extent HTML reports, screenshots on failure.
Bonus	Data-driven, parallel, cross-browser ready.
