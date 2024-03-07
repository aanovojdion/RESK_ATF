package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddUserPage extends BasePage {
    @FindBy(xpath = "//h1[text()='Add User']")
    private WebElement pageTitleElement;
    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement inputFirstNameElement;
    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement inputLastNameElement;
    @FindBy(xpath = "//input[@id='email']")
    private WebElement inputEmailElement;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement inputPasswordElement;

    @FindBy(xpath = "//span[@id='error']")
    private WebElement errorMessageElement;

    public AddUserPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitleElement() {
        return pageTitleElement;
    }

    public WebElement getErrorMessageElement() {
        return errorMessageElement;
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {
        inputFirstNameElement.sendKeys(firstName);
        inputLastNameElement.sendKeys(lastName);
        inputEmailElement.sendKeys(email);
        inputPasswordElement.sendKeys(password);
        submitButton();
    }
}
