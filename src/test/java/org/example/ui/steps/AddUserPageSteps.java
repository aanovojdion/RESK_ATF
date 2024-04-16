package org.example.ui.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configurations.PropertyLoader;
import org.example.configurations.context.ScenarioContext;
import org.example.ui.pages.AddUserPage;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Map;

import static org.example.configurations.context.ScenarioContext.ContextKeys.DRIVER;
import static org.example.utils.BrowserActions.assertVisibilityOf;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUserPageSteps {

    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private final AddUserPage addUserPage = new AddUserPage((scenarioContext.getContext(DRIVER, WebDriver.class)));
    private final String addUserPageUrl = PropertyLoader.getProperty("browser.add_user_url");
    private static final Logger logger = LogManager.getLogger(AddUserPageSteps.class);


    @When("user submits registration form with invalid data")
    public void userSubmitsRegistrationFormWithInvalidData(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String firstName = row.get("firstName").equals("<empty>") ? "" : row.get("firstName");
            String lastName = row.get("lastName").equals("<empty>") ? "" : row.get("lastName");
            String email = row.get("email").equals("<empty>") ? "" : row.get("email");
            String password = row.get("password").equals("<empty>") ? "" : row.get("password");
            addUserPage.fillRegistrationForm(firstName, lastName, email, password);
        }
        logger.info("User submits registration form with invalid data");
    }

    @Then("user is not registered")
    public void userIsNotRegistered() {
        assertEquals("Add User", addUserPage.getPageTitleElement().getText());
        assertEquals(addUserPageUrl, (scenarioContext.getContext(DRIVER, WebDriver.class)).getCurrentUrl());
        logger.info("User is not registered.");
    }

    @Then("an error message is displayed on the screen")
    public void anErrorMessageIsDisplayedOnTheScreen() {
        assertVisibilityOf(addUserPage.getErrorMessageElement());
        assertTrue(addUserPage.getErrorMessageElement().isDisplayed());
        logger.debug("An error message is displayed on the screen");
    }

    @When("user submits registration form with already registered user data")
    public void fillRegistrationForm(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String firstName = row.get("firstName");
            String lastName = row.get("lastName");
            String email = row.get("email");
            String password = row.get("password");
            addUserPage.fillRegistrationForm(firstName, lastName, email, password);
        }
        logger.info("User submits registration form with already registered user data");
    }

    @Then("the {string} error message is displayed on the screen")
    public void theErrorMessageIsDisplayedOnTheScreen(String expectedErrorMessage) {
        assertVisibilityOf(addUserPage.getErrorMessageElement());
        assertEquals(expectedErrorMessage, addUserPage.getErrorMessageElement().getText());
        logger.info("The 'Email address is already in use' error message is displayed");
    }
}