package org.example.utils;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configurations.drivers.DriverManager;
import org.example.hooks.Hooks;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtil {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    public static void takeScreenShot(Scenario scenario) {
        TakesScreenshot screenShot = ((TakesScreenshot) DriverManager.getDriver());
        byte[] scrFile = screenShot.getScreenshotAs(OutputType.BYTES);
        scenario.attach(scrFile, "image/png", "SCREENSHOT");
        try {
            FileUtils.writeByteArrayToFile(new File("target/screenshots/"
                    + new SimpleDateFormat("dd-MMM-yyyy"
                    + " "
                    + scenario.getName()
                    + ".png").format(new Date())), scrFile);
        } catch (IOException e) {
            logger.info("Failed to save screenshot");
        }
    }
}