package com.example.framework.drivers;

import com.example.framework.config.ConfigReader;
import com.example.framework.enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppiumDriverConfig {
    public static AppiumDriver getAppiumDriver(PlatformType platformType) {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            String appiumServer = ConfigReader.getAppiumServer();

            if (platformType == PlatformType.ANDROID) {
                caps.setCapability("platformName", "Android");
                caps.setCapability("deviceName", ConfigReader.getProperty("android.device.name"));
                caps.setCapability("app", ConfigReader.getProperty("android.app.path"));
                return new AndroidDriver(new URL(appiumServer), caps);
            } else if (platformType == PlatformType.IOS) {
                caps.setCapability("platformName", "iOS");
                caps.setCapability("deviceName", ConfigReader.getProperty("ios.device.name"));
                caps.setCapability("app", ConfigReader.getProperty("ios.app.path"));
                return new IOSDriver(new URL(appiumServer), caps);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize Appium driver", e);
        }
        throw new IllegalArgumentException("Unsupported platform: " + platformType);
    }
}