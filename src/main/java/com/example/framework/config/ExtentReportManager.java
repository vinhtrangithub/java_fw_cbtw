package com.example.framework.config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static final ExtentReports extentReports = new ExtentReports();
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static {
        ExtentSparkReporter spark = new ExtentSparkReporter("reports/extent-report.html");
        extentReports.attachReporter(spark);
    }

    public static ExtentTest createTest(String testName) {
        ExtentTest test = extentReports.createTest(testName);
        extentTest.set(test);
        return test;
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void flush() {
        extentReports.flush();
    }
}