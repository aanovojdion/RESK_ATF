package org.example.pages;

import org.example.drivers.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    private final WebDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitBtn;

    public void submitButton() {
        submitBtn.click();
    }
}