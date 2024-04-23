package org.example.configurations;

import org.apache.logging.log4j.LogManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private PropertyLoader() {
    }

    private static Properties properties;

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream ip = new FileInputStream("src/test/resources/application.properties")) {
                properties.load(ip);
            } catch (IOException e) {
                LogManager.getLogger().error("Failed to load properties file: " + e.getMessage());
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
            LogManager.getLogger().error("Property not found for key: " + key);
            throw new RuntimeException("Property not found for key: " + key);
        }
        return properties.getProperty(key);
    }
}