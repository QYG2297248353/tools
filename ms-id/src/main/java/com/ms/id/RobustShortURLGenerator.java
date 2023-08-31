package com.ms.id;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Short URL Generator
 *
 * @author ms
 */
public class RobustShortURLGenerator {
    private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = ALPHANUMERIC.length();
    private static final int SHORT_URL_LENGTH = 6;

    private static final int MAX_ATTEMPTS = 10;

    private static Map<String, String> shortToOriginalMap = new HashMap<>();
    private static Map<String, String> originalToShortMap = new HashMap<>();
    private static Random random = new Random();

    private RobustShortURLGenerator() {
    }

    /**
     * Generate short URL
     *
     * @param originalURL original URL
     * @return short URL
     */
    public static String generateShortURL(String originalURL) {
        if (originalToShortMap.containsKey(originalURL)) {
            return originalToShortMap.get(originalURL);
        }

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            String shortURL = generateRandomShortURL();
            if (!shortToOriginalMap.containsKey(shortURL)) {
                shortToOriginalMap.put(shortURL, originalURL);
                originalToShortMap.put(originalURL, shortURL);
                return shortURL;
            }
        }

        throw new RuntimeException("Failed to generate unique short URL after " + MAX_ATTEMPTS + " attempts.");
    }

    private static String generateRandomShortURL() {
        StringBuilder shortURL = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortURL.append(ALPHANUMERIC.charAt(random.nextInt(BASE)));
        }
        return shortURL.toString();
    }

    /**
     * Generate short URL
     *
     * @param originalURL original URL
     * @param length      length
     * @return short URL
     */
    public static String generateShortURL(String originalURL, int length) {
        if (originalToShortMap.containsKey(originalURL)) {
            return originalToShortMap.get(originalURL);
        }

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            String shortURL = generateRandomShortURL(length);
            if (!shortToOriginalMap.containsKey(shortURL)) {
                shortToOriginalMap.put(shortURL, originalURL);
                originalToShortMap.put(originalURL, shortURL);
                return shortURL;
            }
        }

        throw new RuntimeException("Failed to generate unique short URL after " + MAX_ATTEMPTS + " attempts.");
    }

    private static String generateRandomShortURL(int length) {
        StringBuilder shortURL = new StringBuilder();
        for (int i = 0; i < length; i++) {
            shortURL.append(ALPHANUMERIC.charAt(random.nextInt(BASE)));
        }
        return shortURL.toString();
    }
}