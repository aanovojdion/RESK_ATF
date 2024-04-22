package org.example.configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private PropertyLoader() {
    }

    private static final Logger logger = LogManager.getLogger(PropertyLoader.class);

    private static Properties properties;

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream ip = new FileInputStream("src/test/resources/application.properties")) {
                properties.load(ip);
            } catch (IOException e) {
                logger.info("Failed to load properties file: " + e.getMessage());
                throw new RuntimeException("Failed to load properties file", e);
            }
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        String value = properties.getProperty(key);
        if (value == null) {
            logger.info("Property not found for key: " + key);
            throw new RuntimeException("Property not found for key: " + key);
        }
        return properties.getProperty(key);
    }
}