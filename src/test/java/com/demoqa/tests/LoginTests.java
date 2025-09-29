package com.demoqa.tests;

import com.demoqa.pages.LoginPage;
import com.demoqa.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void validLogin(){
        LoginPage lp = new LoginPage(driver);
        lp.open();
        lp.login("testuser", "Password@123");
        ProfilePage pp = new ProfilePage(driver);
        Assert.assertTrue(pp.isAt(), "Should land on Profile page after valid login");
    }

    @Test
    public void invalidLoginShowsError(){
        LoginPage lp = new LoginPage(driver);
        lp.open();
        lp.login("invalid", "wrongpass");
        Assert.assertTrue(lp.getError().length() > 0, "Error message should appear for invalid login");
    }

    @Test
    public void requiredFieldValidation(){
        LoginPage lp = new LoginPage(driver);
        lp.open();
        // Click login without entering creds
        lp.login("", "");
        Assert.assertTrue(lp.getError().length() > 0 || driver.getCurrentUrl().contains("/login"),
                "Should remain on login page or see validation error");
    }
}
