package org.example.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {

    private PropertyLoader() {
    }

    private static Properties properties;

    private static void loadProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream ip = new FileInputStream("src/test/java/org/example/config/application.properties")) {
                properties.load(ip);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(key);

    }
}
