package org.example.configurations.drivers;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.example.configurations.PropertyLoader.getProperty;

public class DriverManager {

    private static final String browserOptions = getProperty("browser.options.headless");
    private static WebDriver driver;

    private DriverManager() {
        driver = getManager();
    }

    private static WebDriver getManager() {
        String browser = getProperty("browser.type").toLowerCase().trim();
        return switch (browser) {
            case "chrome" -> getChromeDriver();
            case "firefox" -> getFirefoxDriver();
            default -> {
                LogManager.getLogger().warn("Unexpected browser type: " + browser);
                LogManager.getLogger().info("Initializing Chrome browser as default");
                yield getChromeDriver();
            }
        };
    }

    private static WebDriver getChromeDriver() {
        driver = new ChromeDriver(getChromeOptions());
        LogManager.getLogger().info("WebDriver instance created for Chrome browser");
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        if (Boolean.parseBoolean(getProperty("browser.options.headless"))) {
            LogManager.getLogger().info("Chrome will be set to run in headless mode");
            return new ChromeOptions().addArguments("headless");
        }
        return new ChromeOptions();
    }

    private static WebDriver getFirefoxDriver() {
        driver = new FirefoxDriver(getFireFoxOptions());
        LogManager.getLogger().info("WebDriver instance created for FireFox browser");
        return driver;
    }

    private static FirefoxOptions getFireFoxOptions() {
        return new FirefoxOptions().addArguments(browserOptions);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                new DriverManager();
            } catch (Exception ex) {
                LogManager.getLogger().error("WebDriver has not been initialized.");
                throw new IllegalStateException("WebDriver has not been initialized.");
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            LogManager.getLogger().info("WebDriver has been quit and reset.");
        }
    }
}