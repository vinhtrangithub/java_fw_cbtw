package com.example.framework.utils;

import java.security.SecureRandom;

public final class RandomUtils {

    private static final String ALPHA_NUMERIC =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final SecureRandom RANDOM = new SecureRandom();

    private RandomUtils() {
        // prevent instantiation
    }

    /**
     * Generate random alphanumeric string with given length
     */
    private static String generateRandomString(int length) {

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHA_NUMERIC.length());
            sb.append(ALPHA_NUMERIC.charAt(index));
        }

        return sb.toString();
    }

    /**
     * Generate unique title
     * Format: TEST-XXXXXXX (total length = 12)
     */
    public static String generateUniqueTitle() {
        return "TEST-" + generateRandomString(7);
    }

    /**
     * Generate CTF flag with random 4–5 characters
     * Example: CTFlearn{Ab12}
     */
    public static String generateCTFFlag() {
        int length = 4 + RANDOM.nextInt(2); // 4 or 5
        return generateCTFFlag(length);
    }

    /**
     * Generate CTF flag with fixed length (4 or 5)
     */
    public static String generateCTFFlag(int length) {

        if (length < 4 || length > 5) {
            throw new IllegalArgumentException(
                    "Flag length must be 4 or 5 characters");
        }

        return "CTFlearn{" + generateRandomString(length) + "}";
    }

    /**
     * Validate CTF flag format
     */
    public static boolean isValidCTFFlag(String flag) {
        return flag.matches("^CTFlearn\\{[A-Za-z0-9]{4,5}}$");
    }
}
