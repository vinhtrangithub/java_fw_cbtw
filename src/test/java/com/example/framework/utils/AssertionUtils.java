package com.example.framework.utils;

import com.example.framework.config.ExtentReportManager;
import org.testng.Assert;
import org.openqa.selenium.WebElement;

public class AssertionUtils {

    /**
     * Asserts that two strings are equal.
     */
    public static void assertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            ExtentReportManager.getTest().pass(message + " | Actual: " + actual + ", Expected: " + expected);
        } catch (AssertionError e) {
            ExtentReportManager.getTest().fail(message + " | Actual: " + actual + ", Expected: " + expected);
            throw e;
        }
    }

    /**
     * Asserts that a condition is true.
     */
    public static void assertTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            ExtentReportManager.getTest().pass(message);
        } catch (AssertionError e) {
            ExtentReportManager.getTest().fail(message);
            throw e;
        }
    }

    /**
     * Asserts that an element is displayed.
     */
    public static void assertElementDisplayed(WebElement element, String message) {
        try {
            Assert.assertTrue(element.isDisplayed(), message);
            ExtentReportManager.getTest().pass(message);
        } catch (AssertionError e) {
            ExtentReportManager.getTest().fail(message);
            throw e;
        }
    }

    /**
     * Soft assertion to collect multiple failures.
     */
    public static void softAssertEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            ExtentReportManager.getTest().pass(message + " | Actual: " + actual + ", Expected: " + expected);
        } catch (AssertionError e) {
            ExtentReportManager.getTest().warning(message + " | Actual: " + actual + ", Expected: " + expected);
        }
    }
}