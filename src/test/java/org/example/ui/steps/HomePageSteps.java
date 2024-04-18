package org.example.ui.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Transpose;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configurations.PropertyLoader;
import org.example.configurations.context.ScenarioContext;
import org.example.ui.pages.HomePage;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.example.configurations.context.ScenarioContext.ContextKeys.DRIVER;
import static org.example.utils.BrowserActions.assertVisibilityOf;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HomePageSteps {

    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private final HomePage homePage = new HomePage((scenarioContext.getContext(DRIVER, WebDriver.class)));
    private final String validEmail = PropertyLoader.getProperty("credentials.email");
    private final String validPassword = PropertyLoader.getProperty("credentials.password");
    private static final Logger logger = LogManager.getLogger(HomePageSteps.class);


    @Given("user is on the Home page")
    public void userIsOnTheHomePage() {
        (scenarioContext.getContext(DRIVER, WebDriver.class)).get(PropertyLoader.getProperty("browser.homepage_url"));
        logger.info("User is on the Home page");
    }

    //TODO: URL to ENUM

    @When("user logins with valid {string} and {string}")
    public void fillLoginForm(String email, String password) {
        homePage.fillLoginForm(email, password);
        logger.info("User logins with valid data.");
    }


    @When("user trying to login with invalid data")
    public void userLoginsWithInvalidData(@Transpose DataTable loginTable) {
        Map<String, String> invalidCredentials = loginTable.asMap();
        String email = invalidCredentials.get("Email").equals("<empty>") ? "" : invalidCredentials.get("Email");
        String password = invalidCredentials.get("Password").equals("<empty>") ? "" : invalidCredentials.get("Password");
        homePage.fillLoginForm(email, password);
        logger.info("User is trying to log in with invalid data.");
    }

    @Then("user has not logged in")
    public void userHasNotLoggedIn() {
        assertEquals("Contact List App", homePage.getPageTitleElement().getText());
        logger.info("User is not logged in.");
    }

    @Then("the {string} error message is displayed")
    public void theErrorMessageIsDisplayed(String expectedErrorMessage) {
        assertVisibilityOf(homePage.getErrorMessageElement());
        assertEquals(expectedErrorMessage, homePage.getErrorMessageElement().getText());
        logger.info("The 'Incorrect username or password' error message is displayed");
    }

    // @Registration_TC02
    @And("user navigates to the Add User page")
    public void userNavigatesToTheAddUserPage() {
        homePage.signInBtn();
    }

    @Given("already logged-in user is on the Contact List page")
    public void userLoginsWithValidData() {
        userIsOnTheHomePage();
        homePage.fillLoginForm(validEmail, validPassword);
        logger.info("User logins with valid data.");
    }

    @And("the Contact List is empty")
    public void contactListIsEmpty() {

    }

    @And("newly added user is displayed in the table")
    public void newlyAddedUserIsDisplayedInTheTable() {
        logger.info("New Contact is successfully added.");
    }

}