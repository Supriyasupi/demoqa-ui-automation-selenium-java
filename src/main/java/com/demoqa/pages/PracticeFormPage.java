package com.demoqa.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class PracticeFormPage extends BasePage {
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By female = By.xpath("//label[text()='Female']");
    private final By mobile = By.id("userNumber");
    private final By dobInput = By.id("dateOfBirthInput");
    private final By monthSelect = By.className("react-datepicker__month-select");
    private final By yearSelect = By.className("react-datepicker__year-select");
    private final String dayCellXpath = "//div[contains(@class,'react-datepicker__day') and text()='%s']";

    private final By subjectsInput = By.id("subjectsInput");
    private final By hobbiesReading = By.xpath("//label[text()='Reading']");
    private final By hobbiesMusic = By.xpath("//label[text()='Music']");
    private final By address = By.id("currentAddress");
    private final By state = By.id("react-select-3-input");
    private final By city = By.id("react-select-4-input");
    private final By upload = By.id("uploadPicture");
    private final By submit = By.id("submit");
    private final By modalTitle = By.id("example-modal-sizes-title-lg");
    private final By modalContent = By.className("table-responsive");

    public PracticeFormPage(WebDriver driver) { super(driver); }

    public void open(){ driver.get("https://demoqa.com/automation-practice-form"); }

    public void fillBasic(String fn, String ln, String em, String mob){
        type(firstName, fn);
        type(lastName, ln);
        type(email, em);
        safeClick(female);
        type(mobile, mob);
    }

    // ✅ Select DOB from date picker
    public void setDOB(String day, String month, String year){
        // Open calendar
        $(dobInput).click();

        // Select month
        Select monthDropdown = new Select($(monthSelect));
        monthDropdown.selectByVisibleText(month);

        // Select year
        Select yearDropdown = new Select($(yearSelect));
        yearDropdown.selectByVisibleText(year);

        // Select day (avoid leading zeros)
        WebElement dayCell = driver.findElement(By.xpath(String.format(dayCellXpath, Integer.parseInt(day))));
        dayCell.click();
    }

    public void setSubjects(String... subs){
        WebElement s = $(subjectsInput);
        for (String sub: subs){
            s.sendKeys(sub);
            s.sendKeys(Keys.ENTER);
        }
    }

    public void setHobbies(){
        safeClick(hobbiesReading);
        safeClick(hobbiesMusic);
    }

    public void setAddress(String addr, String st, String ct){
        type(address, addr);
        WebElement stIn = $(state);
        stIn.sendKeys(st);
        stIn.sendKeys(Keys.ENTER);
        WebElement ctIn = $(city);
        ctIn.sendKeys(ct);
        ctIn.sendKeys(Keys.ENTER);
    }

    public void uploadFile(String path){
        $(upload).sendKeys(path);
    }

    public void submit(){
        safeClick(submit);
    }

    public boolean isConfirmationShown(){
        try { return $(modalTitle).isDisplayed(); }
        catch (Exception e){ return false; }
    }

    public String modalText(){
        try { return $(modalContent).getText(); }
        catch (Exception e){ return ""; }
    }
}
