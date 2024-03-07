package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddContactPage extends BasePage {
    @FindBy(xpath = "//h1[text()='Add Contact']")
    private WebElement pageTitleElement;
    @FindBy(xpath = "//input[@id='firstName']")
    private WebElement inputFirstNameElement;

    @FindBy(xpath = "//input[@id='lastName']")
    private WebElement inputLastNameElement;
    @FindBy(xpath = "//button[@id='add-contact']")
    private WebElement addContactButtonElement;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement submitBtnElement;

    @FindBy(xpath = "//span[@id='error']")
    private WebElement errorMessageElement;

    public AddContactPage(WebDriver driver) {
        super(driver);
    }


    public WebElement getPageTitleElement() {
        return pageTitleElement;
    }

    public WebElement getInputFirstNameElement() {
        return inputFirstNameElement;
    }

    public WebElement getInputLastNameElement() {
        return inputLastNameElement;
    }

    public WebElement getAddContactButtonElement() {
        return addContactButtonElement;
    }

    public WebElement getSubmitBtnElement() {
        return submitBtnElement;
    }

    public WebElement getErrorMessageElement() {
        return errorMessageElement;
    }

    public void addANewContactBtn() {
        addContactButtonElement.click();
    }

    public void fillAddContactForm(String email, String password) {
        inputFirstNameElement.sendKeys(email);
        inputLastNameElement.sendKeys(password);
        submitButton();
    }
}