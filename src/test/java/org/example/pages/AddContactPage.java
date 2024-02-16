package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddContactPage extends BasePage {
    @FindBy(xpath = "//h1")
    private WebElement pageTitle;

    @FindBy(xpath = "//button[@id='add-contact']")
    private WebElement addContactBtn;
    @FindBy(className = "contacts")
    private WebElement contactsTable;

    public AddContactPage() {
        super();
    }

    public WebElement assertHeader() {
        return pageTitle;
    }

//    public void () {
//
//    }
}