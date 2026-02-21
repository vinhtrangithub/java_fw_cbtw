package com.example.framework.utils;

import com.example.framework.drivers.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    public static String captureScreenshot() {
        try {
            TakesScreenshot ts = (TakesScreenshot) (DriverFactory.getWebDriver() != null
                    ? DriverFactory.getWebDriver() : DriverFactory.getAppiumDriver());
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = "screenshots/failure_" + timestamp + ".png";
            FileUtils.copyFile(source, new File(path));
            return path;
        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }
}