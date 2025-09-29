package com.demoqa.tests;

import com.demoqa.pages.PracticeFormPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PracticeFormTests extends BaseTest {

    @Test
    public void fillPracticeFormAndValidate() throws Exception {
        PracticeFormPage form = new PracticeFormPage(driver);
        form.open();

        form.fillBasic("Supriya", "Tester", "supriya@example.com", "9876543210");

        // Prepare date with SimpleDateFormat
        SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM"); // Full month (e.g. September)
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("28-09-1993");

        String day = sdfDay.format(date);
        String month = sdfMonth.format(date);
        String year = sdfYear.format(date);

        form.setDOB(day, month, year);

        form.setSubjects("Maths", "Physics");
        form.setHobbies();
        form.setAddress("123 Test Street", "NCR", "Delhi");
        form.uploadFile(System.getProperty("user.dir") + "/src/test/resources/testfile.png");

        form.submit();

        Assert.assertTrue(form.isConfirmationShown(), "Confirmation modal should be visible");
        Assert.assertTrue(form.modalText().contains("Supriya"), "Confirmation should include first name");
    }
}
