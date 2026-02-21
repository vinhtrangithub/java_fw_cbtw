package com.example.framework.drivers;

import com.example.framework.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverConfig {
    public static WebDriver getWebDriver() {
        String browser = ConfigReader.getBrowser();
        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver();
            case "firefox" -> new FirefoxDriver();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}