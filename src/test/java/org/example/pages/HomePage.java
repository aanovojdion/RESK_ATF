package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//h1[text()='Contact List App']")
    private WebElement pageTitleElement;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement inputLoginEmailElement;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement inputLoginPasswordElement;

    @FindBy(xpath = "//button[@id='signup']")
    private WebElement signUpBtnElement;

    @FindBy(xpath = "//span[@id='error']")
    private WebElement errorMessageElement;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitleElement() {
        return pageTitleElement;
    }

    public WebElement getErrorMessageElement() {
        return errorMessageElement;
    }

    public void fillLoginForm(String email, String password) {
        inputLoginEmailElement.sendKeys(email);
        inputLoginPasswordElement.sendKeys(password);
        submitButton();
    }

    public void signInBtn() {
        signUpBtnElement.click();
    }
}