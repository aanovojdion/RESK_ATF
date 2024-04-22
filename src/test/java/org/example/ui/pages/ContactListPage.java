package org.example.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ContactListPage extends BasePage {

    @FindBy(xpath = "//h1[text()='Contact List']")
    private WebElement pageTitle;

    public ContactListPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

}
