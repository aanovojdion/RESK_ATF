package org.example.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.PropertyLoader;
import org.example.context.Context;
import org.example.pages.HomePage;

import java.util.List;
import java.util.Map;

import static org.example.utils.BrowserActions.assertVisibilityOf;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HomePageSteps extends Context {

    private final HomePage homePage = new HomePage(driver);
    private final String homePageUrl = PropertyLoader.getProperty("HOME_URL");
    private final String validEmail = PropertyLoader.getProperty("EMAIL");
    private final String validPassword = PropertyLoader.getProperty("PASSWORD");
    private static final Logger logger = LogManager.getLogger(HomePageSteps.class);

    @Given("user is on the Home page")
    public void userIsOnTheHomePage() {
        driver.get(homePageUrl);
        logger.debug("User is on the Home page");
    }

    // @Login_TC01
    @When("user logins with valid {string} and {string}")
    public void fillLoginForm(String emailCred, String passwordCred) {
        homePage.fillLoginForm(emailCred, passwordCred);
        logger.info("User logins with valid data.");
    }


    // @Login_TC02
    @When("user trying to login with invalid data")
    public void userLoginsWithInvalidData(DataTable loginTable) {
        List<Map<String, String>> rows = loginTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String email = row.get("Email").equals("<empty>") ? "" : row.get("Email");
            String password = row.get("Password").equals("<empty>") ? "" : row.get("Password");
            homePage.fillLoginForm(email, password);
            logger.info("User is trying to log in with invalid data.");

//            if (row.get("Email").equals("<empty>") && row.get("Password").equals("<empty>")) {
//                homePage.fillLoginForm("", "");
//            } else if (row.get("Email").equals("<empty>")) {
//                String password = row.get("Password");
//                homePage.fillLoginForm("", password);
//            } else if (row.get("Password").equals("<empty>")) {
//                String email = row.get("Email");
//                homePage.fillLoginForm(email, "");
//            } else {
//                String email = row.get("Email");
//                String password = row.get("Password");
//                homePage.fillLoginForm(email, password);
        }
    }


    //TODO read about null pointer Exception
    @Then("user is not logged in")
    public void userIsNotLoggedIn() {
        assertEquals("Contact List App", homePage.getPageTitleElement().getText());
        assertEquals(homePageUrl, driver.getCurrentUrl());
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
        driver.get(homePageUrl);
        logger.info("User is on the Home page");
        homePage.fillLoginForm(validEmail, validPassword);
        logger.info("User logins with valid data.");
    }

    @And("newly added user is displayed in the table")
    public void newlyAddedUserIsDisplayedInTheTable() {

        logger.info("New Contact is successfully added.");
    }
}