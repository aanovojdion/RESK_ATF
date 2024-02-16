package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//input[@id='email']")
    private WebElement inputLoginEmail;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement inputLoginPassword;


    public HomePage() {
        super();
    }

    public void userLoginsWithValidData(String email, String password) {
        inputLoginEmail.sendKeys(email);
        inputLoginPassword.sendKeys(password);
        submitButton();
    }
}