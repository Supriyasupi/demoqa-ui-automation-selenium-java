package com.demoqa.pages;

import org.openqa.selenium.*;

public class CheckBoxPage extends BasePage {
    private final By expandAll = By.cssSelector("button[title='Expand all']");
    private final By result = By.id("result");

    public CheckBoxPage(WebDriver driver){ super(driver); }

    public void open(){ driver.get("https://demoqa.com/checkbox"); }

    public void expandAll(){
        safeClick(expandAll);
    }

    public void selectNode(String title){
        By cb = By.xpath("//span[@class='rct-title' and text()='"+title+"']/preceding-sibling::span[@class='rct-checkbox']");
        safeClick(cb);
    }

    public String getOutput(){
        try { return $(result).getText(); }
        catch (Exception e){ return ""; }
    }
}
