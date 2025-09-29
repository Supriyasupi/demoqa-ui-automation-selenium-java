package com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WebTablesPage extends BasePage {

    private final By addButton = By.id("addNewRecordButton");
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By age = By.id("age");
    private final By salary = By.id("salary");
    private final By department = By.id("department");
    private final By submit = By.id("submit");

    public WebTablesPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://demoqa.com/webtables");
    }

    public void addUser(String fn, String ln, String em, String ag, String sal, String dept) {
        safeClick(addButton);
        type(firstName, fn);
        type(lastName, ln);
        type(email, em);
        type(age, ag);
        type(salary, sal);
        type(department, dept);
        safeClick(submit);
    }

    /**
     * Finds age for a given email across all table pages.
     */
    public String getAgeForEmail(String emailId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        while (true) {
            List<WebElement> rows = driver.findElements(By.cssSelector(".rt-tr-group"));
            for (WebElement row : rows) {
                if (row.getText().contains(emailId)) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", row);
                    List<WebElement> cells = row.findElements(By.className("rt-td"));
                    return cells.get(2).getText().trim();
                }
            }

            // pagination
            List<WebElement> nextBtns = driver.findElements(By.cssSelector(".-next button"));
            if (!nextBtns.isEmpty() && nextBtns.get(0).isEnabled()) {
                nextBtns.get(0).click();
                wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
            } else {
                break;
            }
        }

        throw new NoSuchElementException("User with email " + emailId + " not found in table.");
    }

    /**
     * Edits age for a given email across all table pages.
     */
    public void editAge(String emailId, String newAge) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        while (true) {
            List<WebElement> rows = driver.findElements(By.cssSelector(".rt-tr-group"));
            for (WebElement row : rows) {
                if (row.getText().contains(emailId)) {
                    WebElement editBtn = row.findElement(By.cssSelector("span[title='Edit']"));
                    editBtn.click();

                    WebElement ageInput = wait.until(ExpectedConditions.visibilityOfElementLocated(age));

                    String selectAll = System.getProperty("os.name").toLowerCase().contains("mac")
                            ? Keys.chord(Keys.COMMAND, "a")
                            : Keys.chord(Keys.CONTROL, "a");

                    ageInput.sendKeys(selectAll);
                    ageInput.sendKeys(Keys.DELETE);
                    ageInput.sendKeys(newAge);

                    safeClick(submit);

                    // wait until updated
                    wait.until(ExpectedConditions.textToBePresentInElement(
                            row.findElements(By.className("rt-td")).get(2), newAge
                    ));
                    return;
                }
            }

            // pagination
            List<WebElement> nextBtns = driver.findElements(By.cssSelector(".-next button"));
            if (!nextBtns.isEmpty() && nextBtns.get(0).isEnabled()) {
                nextBtns.get(0).click();
                wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
            } else {
                break;
            }
        }

        throw new NoSuchElementException("User with email " + emailId + " not found to edit.");
    }

    /**
     * Deletes user row by email across all table pages.
     */
    public void deleteUser(String emailId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        while (true) {
            List<WebElement> rows = driver.findElements(By.cssSelector(".rt-tr-group"));
            for (WebElement row : rows) {
                if (row.getText().contains(emailId)) {
                    WebElement deleteBtn = row.findElement(By.cssSelector("span[title='Delete']"));
                    deleteBtn.click();
                    wait.until(ExpectedConditions.stalenessOf(row));
                    return;
                }
            }

            // pagination
            List<WebElement> nextBtns = driver.findElements(By.cssSelector(".-next button"));
            if (!nextBtns.isEmpty() && nextBtns.get(0).isEnabled()) {
                nextBtns.get(0).click();
                wait.until(ExpectedConditions.stalenessOf(rows.get(0)));
            } else {
                break;
            }
        }

        throw new NoSuchElementException("User with email " + emailId + " not found to delete.");
    }

    public boolean isUserPresent(String emailId) {
        try {
            getAgeForEmail(emailId);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
