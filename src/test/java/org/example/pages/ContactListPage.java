package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ContactListPage extends BasePage {

    @FindBy(xpath = "//h1[text()='Contact List']")
    private WebElement pageTitle;

    @FindBy(xpath = "//button[@id='add-contact']")
    private WebElement addContactBtn;

    @FindBy(className = "contacts")
    private WebElement contactsTable;

    public ContactListPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getAddContactBtn() {
        return addContactBtn;
    }

    public WebElement getContactsTable() {
        return contactsTable;
    }
}
