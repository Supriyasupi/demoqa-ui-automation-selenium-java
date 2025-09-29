package com.demoqa.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demoqa.utils.DriverFactory;
import com.demoqa.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener {
    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private ExtentReports extent;

    public static ExtentTest getTest(){ return testThread.get(); }

    @Override
    public void onStart(ISuite suite) {
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().log(Status.PASS, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest t = testThread.get();
        t.fail(result.getThrowable());
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            String path = ExtentManager.takeScreenshot(driver, result.getMethod().getMethodName());
            if (path != null) t.addScreenCaptureFromPath(path);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().log(Status.SKIP, "Skipped");
    }
}
