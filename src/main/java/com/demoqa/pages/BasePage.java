package com.demoqa.pages;

import com.demoqa.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class BasePage {
    protected WebDriver driver;
    protected WaitUtils wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 40);
    }

    protected WebElement $(By locator) {
        return wait.visible(locator);
    }

    protected void type(By locator, String text) {
        WebElement el = $(locator);
        el.clear();
        el.sendKeys(text);
    }

    protected void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
    }

    protected void jsClick(WebElement el) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected void safeClick(By locator) {
        WebElement el = wait.clickable(locator);
        try {
            new Actions(driver).moveToElement(el).pause(java.time.Duration.ofMillis(200)).perform();
            scrollIntoView(el);
            el.click();
        } catch (Exception e) {
            try {
                scrollIntoView(el);
                jsClick(el);
            } catch (Exception ex) {
                jsClick(el);
            }
        }
    }
}
