package com.demoqa.tests;

import com.demoqa.pages.WebTablesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTablesTests extends BaseTest {

    @Test
    public void tableAddEditDelete() {
        WebTablesPage tables = new WebTablesPage(driver);
        tables.open();

        String email = "testuser@demo.com";

        // Add user
        tables.addUser("Test", "User", email, "30", "50000", "QA");
        Assert.assertEquals(tables.getAgeForEmail(email), "30", "Age should be 30");

        // Edit age
        tables.editAge(email, "31");
        Assert.assertEquals(tables.getAgeForEmail(email), "31", "Age should be updated to 31");

        // Delete user
        tables.deleteUser(email);
        Assert.assertFalse(tables.isUserPresent(email), "User should be deleted");
    }
}
