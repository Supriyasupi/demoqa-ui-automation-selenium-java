package com.demoqa.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportDir = "test-output/ExtentReports/";
            new File(reportDir).mkdirs();
            ExtentSparkReporter spark = new ExtentSparkReporter(reportDir + "resultreport.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("DemoQA UI Automation");
            spark.config().setReportName("Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static String takeScreenshot(WebDriver driver, String name) {
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String path = "test-output/ExtentReports/screenshots/" + name + "_" + ts + ".png";
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
