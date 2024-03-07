package org.example.utils;

import org.example.drivers.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserActions {
    static WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 10);

    public static WebElement assertVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}