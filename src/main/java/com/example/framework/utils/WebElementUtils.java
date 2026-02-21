package com.example.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebElementUtils {
    private WebDriver driver;
    private Actions actions;
    private JavascriptExecutor jsExecutor;

    public WebElementUtils(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
    }
    
    /**
     * Converts a string locator (e.g., "id=exampleId") to a By object.
     */
    private By parseLocator(String locator) {
        if (locator.startsWith("id=")) {
            return By.id(locator.substring(3));
        } else if (locator.startsWith("name=")) {
            return By.name(locator.substring(5));
        } else if (locator.startsWith("xpath=")) {
            return By.xpath(locator.substring(6));
        } else if (locator.startsWith("css=")) {
            return By.cssSelector(locator.substring(4));
        } else if (locator.startsWith("class=")) {
            return By.className(locator.substring(6));
        } else if (locator.startsWith("tag=")) {
            return By.tagName(locator.substring(4));
        } else {
            throw new IllegalArgumentException("Invalid locator format: " + locator);
        }
    }

    /**
     * Wrapper method for finding an element with error handling.
     */
    public WebElement getElement(String locator) {
        try {
            By by = parseLocator(locator);
            // Wait for the element to be present (you can customize this timeout)
            WaitUtils.waitForElementVisible(driver, driver.findElement(by));
            return driver.findElement(by);
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + locator);
            return null; // Return null if the element is not found
        } catch (TimeoutException e) {
            System.err.println("Timeout while waiting for element: " + locator);
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while finding element: " + locator);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Wrapper method for finding multiple elements.
     */
    public java.util.List<WebElement> getElements(String locator) {
        try {
            By by = parseLocator(locator);
            return driver.findElements(by);
        } catch (Exception e) {
            System.err.println("An error occurred while finding elements: " + locator);
            e.printStackTrace();
            return java.util.Collections.emptyList(); // Return an empty list if an error occurs
        }
    }

    /**
     * Finds an element with retries.
     */
    public WebElement getElementWithRetry(String locator, int retries) {
        int attempts = 0;
        while (attempts < retries) {
            try {
                By by = parseLocator(locator);
                return driver.findElement(by);
            } catch (NoSuchElementException e) {
                attempts++;
                System.out.println("Retrying to find element: " + locator + " (Attempt " + attempts + ")");
            }
        }
        System.err.println("Element not found after " + retries + " attempts: " + locator);
        return null;
    }

    /**
     * Navigates to the specified URL (for web testing).
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Gets the current page title.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Clicks an element after ensuring it is clickable.
     */
    public void clickElement(String locator) {
        WebElement element = getElement(locator);
        WaitUtils.waitForElementClickable(driver, element);
        element.click();
    }

    /**
     * Enters text into an element after ensuring it is visible.
     */
    public void enterText(String locator, String text) {
        WebElement element = getElement(locator);
        WaitUtils.waitForElementVisible(driver, element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Gets text from an element.
     */
    public String getElementText(String locator) {
        WebElement element = getElement(locator);
        WaitUtils.waitForElementVisible(driver, element);
        return element.getText();
    }

    /**
     * Selects an option from a dropdown by visible text.
     */
    public void selectDropdownByText(String locator, String text) {
        WebElement element = getElement(locator);
        WaitUtils.waitForElementVisible(driver, element);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    /**
     * Performs a hover action over an element.
     */
    public void hoverOverElement(String locator) {
        WebElement element = getElement(locator);
        WaitUtils.waitForElementVisible(driver, element);
        actions.moveToElement(element).build().perform();
    }

    /**
     * Executes JavaScript to scroll to an element.
     */
    public void scrollToElement(String locator) {
        WebElement element = getElement(locator);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Checks if an element is displayed.
     */
    public boolean isElementDisplayed(String locator) {
        try {
            WebElement element = getElement(locator);
            WaitUtils.waitForElementVisible(driver, element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Switches to a frame by WebElement.
     */
    public void switchToFrame(String locator) {
        WebElement frameElement = getElement(locator);
        WaitUtils.waitForElementVisible(driver, frameElement);
        driver.switchTo().frame(frameElement);
    }

    /**
     * Switches back to the default content.
     */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    /**
     * Executes arbitrary JavaScript code.
     */
    public Object executeJavaScript(String script, Object... args) {
        return jsExecutor.executeScript(script, args);
    }

    /**
     * Waits for the page to load (basic check using document.readyState).
     */
    public void waitForPageLoad() {
        WaitUtils.waitForPageLoad(driver);
    }
    /**
     * Waits for an element to be visible.
     */
    public void waitForElementVisible(String locator) {
        WebElement element = getElement(locator);
        WaitUtils.waitForElementVisible(driver, element);
    }

}
