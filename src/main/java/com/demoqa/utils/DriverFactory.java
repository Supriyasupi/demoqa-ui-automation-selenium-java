package com.demoqa.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        for (int i = 0; i < 3; i++) {
            try {
                if ("firefox".equalsIgnoreCase(browser)) {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions fo = new FirefoxOptions();
                    tlDriver.set(new FirefoxDriver(fo));
                } else {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions co = new ChromeOptions();
                    co.addArguments("--start-maximized");
                    tlDriver.set(new ChromeDriver(co));
                }
                getDriver().manage().deleteAllCookies();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                try { Thread.sleep(1500);} catch (InterruptedException ie) {}
            }
        }
        throw new RuntimeException("Failed to start WebDriver after retries");
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        WebDriver d = tlDriver.get();
        if (d != null) {
            d.quit();
            tlDriver.remove();
        }
    }
}
