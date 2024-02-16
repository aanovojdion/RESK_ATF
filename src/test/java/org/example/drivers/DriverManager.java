package org.example.drivers;

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

    private DriverManager() {
    }

    public static WebDriver getManager() {
        String browser = PropertyLoader.getProperty("browser");
        switch (browser) {
            case "CHROME":
                return getChromeDriver();
            case "FIREFOX":
                return getFirefoxDriver();
            default:
                throw new RuntimeException("Unexpected browser type: " + browser);
        }
    }

    private static WebDriver getChromeDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            try {
                options.setHeadless(Boolean.parseBoolean(getProperty("headless")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(110, TimeUnit.SECONDS);
//            driver.manage().window().maximize();
        }
        return driver;
    }

    private static WebDriver getFirefoxDriver() {
        if (driver == null) {
            FirefoxOptions options = new FirefoxOptions();
            try {
                options.setHeadless(Boolean.parseBoolean(getProperty("headless")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.setProperty("webdriver.ghecko.driver", "src/test/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
//            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return getManager();
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}