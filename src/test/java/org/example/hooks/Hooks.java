package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.actions.DeleteUserActions;
import org.example.api.actions.LoginUserActions;
import org.example.configurations.context.ScenarioContext;
import org.example.configurations.drivers.DriverManager;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.example.configurations.context.ScenarioContext.ContextKeys.DRIVER;


public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    DeleteUserActions deleteUserActions = new DeleteUserActions();
    LoginUserActions loginUserActions = new LoginUserActions();

    @Before
    public void beforeScenario(Scenario scenario) {
        String scenarioName = scenario.getName();
        WebDriver webDriver = DriverManager.getDriver();
        scenarioContext.setContext(DRIVER, webDriver);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Scenario '{}' started.", scenarioName);
    }

//    @Before("@AddContact")
//    public void beforeAddContact() {
//        loginUserActions.userIsUsingValidCredentials();
//        loginUserActions.aRequestToLoginIsSent();
//        DeleteContactsActions.clearContactList();
//    }

    @After("@UserCreation")
    public void afterUserCreation() {
        deleteUserActions.aRequestToDeleteUserIsSent();
        logger.info("The user created for testing purposes was successfully deleted.");
    }

    @After
    public void afterScenario() {
        DriverManager.quitDriver();
    }

//TODO: in @After clean scenario context (teardown)
}