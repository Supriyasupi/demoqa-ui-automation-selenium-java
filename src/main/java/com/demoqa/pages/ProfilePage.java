package com.demoqa.pages;

import org.openqa.selenium.*;

public class ProfilePage extends BasePage {
    private final By header = By.xpath("//div[@class='main-header' and text()='Profile']");
    public ProfilePage(WebDriver driver){ super(driver); }
    public boolean isAt() {
        try {
            return $(header).isDisplayed();
        } catch (Exception e) {
            return driver.getCurrentUrl().contains("/profile");
        }
    }
}
