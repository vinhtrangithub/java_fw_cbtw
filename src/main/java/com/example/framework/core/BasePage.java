package com.example.framework.core;

import com.example.framework.drivers.DriverFactory;
import com.example.framework.utils.WebElementUtils;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebElementUtils webElementUtils;


    public BasePage() {
        // Initialize driver (supports both Selenium WebDriver and AppiumDriver)
        this.driver = DriverFactory.getWebDriver() != null ? DriverFactory.getWebDriver() : DriverFactory.getAppiumDriver();
        this.webElementUtils = new WebElementUtils(driver);
    }

    // Essential Methods
    public WebElementUtils getWebElementActions() {
        return webElementUtils;
    }
    
}