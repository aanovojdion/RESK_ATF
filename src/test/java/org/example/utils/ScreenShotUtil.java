package org.example.utils;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.example.configurations.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShotUtil {

    public static void takeScreenShot(Scenario scenario) {
        TakesScreenshot screenShot = ((TakesScreenshot) DriverManager.getDriver());
        byte[] scrFile = screenShot.getScreenshotAs(OutputType.BYTES);
        scenario.attach(scrFile, "image/png", "SCREENSHOT");
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH.mm.ss.SSSSSS ");
        String formattedDateTime = now.format(formatter);

        try {
            FileUtils.writeByteArrayToFile(new File("target/screenshots/"
                    + formattedDateTime + scenario.getName() + ".png"), scrFile);
        } catch (IOException e) {
            LogManager.getLogger().error("Failed to save screenshot");
        }
    }
}