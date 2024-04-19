package org.example.configurations.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.example.configurations.PropertyLoader.getProperty;

//TODO: Refactor true/false headless from application.properties
public class DriverManager {

    private static String browserOptions = getProperty("browser.options.headless");
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    private DriverManager() {
        driver = getManager();
    }

    private static WebDriver getManager() {
        String browser = getProperty("browser.type").toLowerCase().trim();
        return switch (browser) {
            case "chrome" -> getChromeDriver();
            case "firefox" -> getFirefoxDriver();
            default -> {
                logger.info("Unexpected browser type: " + browser);
                logger.info("Initializing Chrome browser as default");
                yield getChromeDriver();
            }
        };
    }

    private static WebDriver getChromeDriver() {
        driver = new ChromeDriver(getChromeOptions());
        logger.info("WebDriver instance created for Chrome browser");
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        return new ChromeOptions().addArguments(browserOptions);
    }

    private static WebDriver getFirefoxDriver() {
        driver = new FirefoxDriver(getFireFoxOptions());
        logger.info("WebDriver instance created for FireFox browser");
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
                throw new IllegalStateException("WebDriver has not been initialized.");
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver has been quit and reset.");
        }
    }
}