package org.example.ui.steps;

import io.cucumber.java.Transpose;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configurations.context.ScenarioContext;
import org.example.configurations.drivers.DriverManager;
import org.example.ui.pages.AddUserPage;

import java.util.Map;

import static org.example.configurations.context.ScenarioContext.ObjectKeys.EMAIL;
import static org.example.configurations.context.ScenarioContext.ObjectKeys.PASSWORD;
import static org.example.utils.BrowserActions.assertVisibilityOf;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddUserPageSteps {

    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private final AddUserPage addUserPage = new AddUserPage(DriverManager.getDriver());
    private static final Logger logger = LogManager.getLogger(AddUserPageSteps.class);


    @When("user submits registration form with data")
    public void fillRegistrationForm(@Transpose Map<String, String> userData) {
        String firstName = userData.get("firstName").equals("<empty>") ? "" : userData.get("firstName");
        String lastName = userData.get("lastName").equals("<empty>") ? "" : userData.get("lastName");
        String email = userData.get("email").equals("<empty>") ? "" : userData.get("email");
        String password = userData.get("password").equals("<empty>") ? "" : userData.get("password");
        addUserPage.fillRegistrationForm(firstName, lastName, email, password);
        scenarioContext.setContext(EMAIL, email);
        scenarioContext.setContext(PASSWORD, password);
        logger.info("User submits registration form with data");
    }

    @Then("user is not registered")
    public void userIsNotRegistered() {
        assertEquals("Add User", addUserPage.getPageTitleElement().getText());
        logger.info("User is not registered.");
    }

    @Then("an error message is displayed on the screen")
    public void anErrorMessageIsDisplayedOnTheScreen() {
        assertVisibilityOf(addUserPage.getErrorMessageElement());
        assertTrue(addUserPage.getErrorMessageElement().isDisplayed());
        logger.debug("An error message is displayed on the screen");
    }

    @Then("the {string} error message is displayed on the screen")
    public void theErrorMessageIsDisplayedOnTheScreen(String expectedErrorMessage) {
        assertVisibilityOf(addUserPage.getErrorMessageElement());
        assertEquals(expectedErrorMessage, addUserPage.getErrorMessageElement().getText());
        logger.info("The 'Email address is already in use' error message is displayed");
    }
}