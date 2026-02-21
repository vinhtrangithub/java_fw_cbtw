package com.example.framework.drivers;

import com.example.framework.api.ApiClient;
import com.example.framework.enums.PlatformType;
import org.openqa.selenium.WebDriver;
import io.appium.java_client.AppiumDriver;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static final ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();

    public static void setDriver(PlatformType platformType) {
        switch (platformType) {
            case WEB:
                webDriver.set(WebDriverConfig.getWebDriver());
                break;
            case ANDROID:
            case IOS:
                appiumDriver.set(AppiumDriverConfig.getAppiumDriver(platformType));
                break;
            case API:
                ApiClient.init();
                break;
            default:
                throw new IllegalArgumentException("Unsupported platform: " + platformType);
        }
    }

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static AppiumDriver getAppiumDriver() {
        return appiumDriver.get();
    }

    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
        if (appiumDriver.get() != null) {
            appiumDriver.get().quit();
            appiumDriver.remove();
        }
        ApiClient.reset();
    }
}