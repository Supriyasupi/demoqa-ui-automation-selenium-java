package com.demoqa.pages;

import org.openqa.selenium.*;

public class LoginPage extends BasePage {
    private final By user = By.id("userName");
    private final By pass = By.id("password");
    private final By loginBtn = By.id("login");
    private final By error = By.id("name");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() { driver.get("https://demoqa.com/login"); }

    public void login(String username, String password) {
        type(user, username);
        type(pass, password);
        safeClick(loginBtn);
    }

    public String getError() {
        try { return $(error).getText(); }
        catch (Exception e) { return ""; }
    }
}
