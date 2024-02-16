package org.example.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.example.config.PropertyLoader;
import org.example.drivers.DriverManager;
import org.example.pages.ContactListPage;
import org.example.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class StepDefUI {
    private final HomePage homePage;
    private final ContactListPage contactListPage;
    private final WebDriver driver;
    private final String homePageUrl = PropertyLoader.getProperty("homeUrl");

    public StepDefUI(HomePage homePage, ContactListPage contactListPage) {
        this.homePage = homePage;
        this.contactListPage = contactListPage;
        this.driver = DriverManager.getDriver();
    }

    @Given("user is on the Home page")
    public void userIsOnTheHomePage() {
        driver.get(homePageUrl);
    }

    @When("user logins with valid {string} and {string}")
    public void userLoginsWithValidData(String emailCred, String passwordCred) {
//        String email = emailCred;
//        String password = passwordCred;
        homePage.userLoginsWithValidData(emailCred, passwordCred);
    }

    @Then("user is successfully redirected to the Contact List page")
    public void userIsRedirectedToTheContactListPage() {
        WebElement header = contactListPage.assertHeader();
        System.out.println(header.getText());
        String headerText = header.getText();
        Assertions.assertThat(headerText).isEqualTo("Contact List");
    }
}