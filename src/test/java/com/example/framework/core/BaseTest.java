package com.example.framework.core;

import com.example.framework.config.ConfigReader;
import com.example.framework.config.ExtentReportManager;
import com.example.framework.drivers.DriverFactory;
import com.example.framework.enums.PlatformType;
import com.example.framework.utils.ScreenshotUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void setUp(ITestResult result) {
        String platform = System.getProperty("platform", ConfigReader.getProperty("platform").toUpperCase());
        PlatformType platformType = PlatformType.valueOf(platform);
        DriverFactory.setDriver(platformType);
        TestContext.setPlatform(platformType);
        ExtentReportManager.createTest(result.getMethod().getMethodName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ScreenshotUtils.captureScreenshot();
            ExtentReportManager.getTest().fail("Test failed, screenshot: " + screenshotPath);
        }
        DriverFactory.quitDriver();
        TestContext.clearContext();
        ExtentReportManager.flush();
    }
}