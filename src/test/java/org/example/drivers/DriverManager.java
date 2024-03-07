package org.example.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

import static org.example.config.PropertyLoader.getProperty;

public class DriverManager {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    private DriverManager() {
        driver = getManager();
    }

    private static WebDriver getManager() {
        String browser = PropertyLoader.getProperty("BROWSER");
        return switch (browser) {
            case "CHROME" -> getChromeDriver();
            case "FIREFOX" -> getFirefoxDriver();
            default -> throw new RuntimeException("Unexpected browser type: " + browser);
        };
    }

    private static WebDriver getChromeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            try {
                chromeOptions.setHeadless(Boolean.parseBoolean(getProperty("HEADLESS")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver = new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }


    private static WebDriver getFirefoxDriver() {
        if (driver == null) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            try {
                firefoxOptions.setHeadless(Boolean.parseBoolean(getProperty("HEADLESS")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                new DriverManager();
            } catch (Exception ex) {
                throw new IllegalStateException("WebDriver has not been initialized. Call setUpDriver() first.");
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null;
            logger.debug("WebDriver has been quit and reset.");
        }
    }
}