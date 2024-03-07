package org.example.definitions;

import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.PropertyLoader;
import org.example.context.Context;
import org.example.pages.ContactListPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactListPageSteps extends Context {

    ContactListPage contactListPage = new ContactListPage(driver);
    private final String contactListUrl = PropertyLoader.getProperty("CONTACT_LIST_URL");
    private static final Logger logger = LogManager.getLogger(ContactListPageSteps.class);

    @Then("user is successfully redirected to the Contact List page")
    public void userIsRedirectedToTheContactListPage() {
        assertEquals("Contact List", contactListPage.getPageTitle().getText());
        assertEquals(contactListUrl, driver.getCurrentUrl());
        logger.info("User is successfully logged in.");
    }

    @Then("user is redirected to the Contact List page")
    public void userIsOnTheContactListPage() {
        assertEquals("Contact List", contactListPage.getPageTitle().getText());
        assertEquals(contactListUrl, driver.getCurrentUrl());
        logger.info("The Contact List page is displayed.");
    }
}
