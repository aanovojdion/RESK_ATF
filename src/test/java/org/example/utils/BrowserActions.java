package org.example.utils;

import org.example.configurations.drivers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class BrowserActions {
    static WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.of(10, ChronoUnit.SECONDS));

    public static WebElement assertVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}