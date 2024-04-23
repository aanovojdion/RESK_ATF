package org.example.ui.steps;

import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.example.configurations.drivers.DriverManager;
import org.example.ui.pages.ContactListPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactListPageSteps {

    private final ContactListPage contactListPage = new ContactListPage(DriverManager.getDriver());

    @Then("user has been successfully registered")
    public void userHasBeenSuccessfullyRegistered() {
        assertEquals("Contact List", contactListPage.getPageTitle().getText());
        LogManager.getLogger().info("User has successfully logged in.");
    }

    @Then("user has successfully logged in")
    public void userHasLoggedIn() {
        assertEquals("Contact List", contactListPage.getPageTitle().getText());
        LogManager.getLogger().info("User has successfully logged in.");
    }
}