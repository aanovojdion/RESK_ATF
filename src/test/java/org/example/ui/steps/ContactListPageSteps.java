package org.example.ui.steps;

import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configurations.PropertyLoader;
import org.example.configurations.context.ScenarioContext;
import org.example.ui.pages.ContactListPage;
import org.openqa.selenium.WebDriver;

import static org.example.configurations.context.ScenarioContext.ContextKeys.DRIVER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactListPageSteps {

    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private final ContactListPage contactListPage = new ContactListPage((scenarioContext.getContext(DRIVER, WebDriver.class)));
    private final String contactListUrl = PropertyLoader.getProperty("browser.contact_list_url");
    private static final Logger logger = LogManager.getLogger(ContactListPageSteps.class);

    @Then("user has successfully logged in")
    public void userHasLoggedIn() {
        assertEquals("Contact List", contactListPage.getPageTitle().getText());
        assertEquals(contactListUrl, (scenarioContext.getContext(DRIVER, WebDriver.class)).getCurrentUrl());
        logger.info("User has successfully logged in.");
    }

    @Then("user is redirected to the Contact List page")
    public void userIsOnTheContactListPage() {
        assertEquals("Contact List", contactListPage.getPageTitle().getText());
        assertEquals(contactListUrl, (scenarioContext.getContext(DRIVER, WebDriver.class)).getCurrentUrl());
        logger.info("The Contact List page is displayed.");
    }
}