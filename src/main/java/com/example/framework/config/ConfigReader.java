package com.example.framework.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getEnvironment() {
        return getProperty("environment");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getWebUrl() {
        return getProperty(getEnvironment() + ".web.url");
    }

    public static String getAppiumServer() {
        return getProperty("appium.server");
    }

    public static String getApiUrl() {
        return getProperty(getEnvironment() + ".api.url");
    }

    public static String getUsername() {
        return getProperty("username");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static String getApiKey() {
        return getProperty("api.key");
    }
}