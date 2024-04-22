package org.example.ui.steps;

import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.example.configurations.PropertyLoader;
import org.example.configurations.drivers.DriverManager;
import org.example.ui.pages.ContactListPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactListPageSteps {

    private final ContactListPage contactListPage = new ContactListPage(DriverManager.getDriver());
    private final String contactListUrl = PropertyLoader.getProperty("browser.contact_list_url");

    @Then("user has been successfully registered")
    public void userHasBeenSuccessfullyRegistered() {
        assertEquals("Contact List", contactListPage.getPageTitle().getText());
        assertEquals(contactListUrl, DriverManager.getDriver().getCurrentUrl());
        LogManager.getLogger().info("User has successfully logged in.");
    }

    @Then("user has successfully logged in")
    public void userHasLoggedIn() {
        assertEquals("Contact List", contactListPage.getPageTitle().getText());
        LogManager.getLogger().info("User has successfully logged in.");
    }

    @Then("user is redirected to the Contact List page")
    public void userIsOnTheContactListPage() {
        assertEquals("Contact List", contactListPage.getPageTitle().getText());
        LogManager.getLogger().info("The Contact List page is displayed.");
    }
}