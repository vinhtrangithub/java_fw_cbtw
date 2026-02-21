package com.example.framework.core;

import com.example.framework.enums.PlatformType;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private static final ThreadLocal<Map<String, Object>> testContext = ThreadLocal.withInitial(HashMap::new);

    /**
     * Stores a key-value pair in the test context.
     */
    public static void setContext(String key, Object value) {
        testContext.get().put(key, value);
    }

    /**
     * Retrieves a value from the test context by key.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getContext(String key) {
        return (T) testContext.get().get(key);
    }

    /**
     * Stores the platform type for the current test.
     */
    public static void setPlatform(PlatformType platform) {
        setContext("platform", platform);
    }

    /**
     * Retrieves the platform type for the current test.
     */
    public static PlatformType getPlatform() {
        return getContext("platform");
    }

    /**
     * Clears the test context after test execution.
     */
    public static void clearContext() {
        testContext.get().clear();
    }
}