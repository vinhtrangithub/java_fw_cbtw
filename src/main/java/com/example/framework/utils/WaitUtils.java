package com.example.framework.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    /**
     * Waits for an element to be visible.
     */
    public static void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for an element to be clickable.
     */
    public static void waitForElementClickable(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for the page to load (checks document.readyState).
     */
    public static void waitForPageLoad(WebDriver driver) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Waits for an element to be invisible.
     */
    public static void waitForElementInvisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Waits for a specific text to be present in an element.
     */
    public static void waitForTextInElement(WebDriver driver, WebElement element, String text) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}