package org.example.definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.config.PropertyLoader;
import org.example.context.Context;
import org.example.pages.AddContactPage;

import java.util.List;
import java.util.Map;

import static org.example.utils.BrowserActions.assertVisibilityOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddContactPageSteps extends Context {
    private final AddContactPage addContactPage = new AddContactPage(driver);
    private final String addContactUrl = PropertyLoader.getProperty("ADD_CONTACT_URL");
    private static final Logger logger = LogManager.getLogger(ContactListPageSteps.class);


    @And("user clicked the Add a New Contact button")
    public void userClickedTheAddANewContactButton() {
        addContactPage.addANewContactBtn();
        logger.debug("The Add a New Contact button is clicked");
    }

    @When("user submits add a new contact form with required valid data")
    public void userSubmitsFormWithRequiredValidData(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            addContactPage.fillAddContactForm(
                    row.get("firstName"),
                    row.get("lastName")
            );
        }
        logger.info("User submits add a new contact form with required valid data");
    }

    @When("user submits add a new contact form with required invalid data")
    public void userSubmitsFormWithRequiredInvalidData(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String firstName = row.get("firstName").equals("<empty>") ? "" : row.get("firstName");
            String lastName = row.get("lastName").equals("<empty>") ? "" : row.get("lastName");
            addContactPage.fillAddContactForm(firstName, lastName);
        }
        logger.info("User submits add a new contact form with required invalid data");
    }

    @Then("the warning message is displayed")
    public void theWarningMessageIsDisplayed() {
        assertVisibilityOf(addContactPage.getErrorMessageElement());
        assertTrue(addContactPage.getErrorMessageElement().isDisplayed());
        logger.debug("an error message is displayed on the screen.");
    }

    @Then("the Add Contact page is displayed")
    public void theAddContactPageIsDisplayed() {
        assertEquals("Add Contact", addContactPage.getPageTitleElement().getText());
        assertEquals(addContactUrl, driver.getCurrentUrl());
        logger.info("The Add Contact page is displayed.");
        logger.info("A Contact is not added.");
    }

}
