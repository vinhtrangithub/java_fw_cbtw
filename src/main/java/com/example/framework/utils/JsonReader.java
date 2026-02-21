package com.example.framework.utils;

import com.example.framework.enums.PlatformType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonReader {
    public static List<Map<String, String>> readTestData(String fileName) {
        try (InputStream input = JsonReader.class.getClassLoader().getResourceAsStream("testdata/" + fileName)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(input, List.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read test data from " + fileName, e);
        }
    }

    public static PlatformType getPlatformType(String platform) {
        return PlatformType.valueOf(platform.toUpperCase());
    }
}