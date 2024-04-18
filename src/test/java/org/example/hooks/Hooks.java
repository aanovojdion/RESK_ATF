package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.actions.DeleteUserActions;
import org.example.api.actions.LoginUserActions;
import org.example.configurations.context.ScenarioContext;
import org.example.configurations.drivers.DriverManager;
import org.example.utils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.example.configurations.context.ScenarioContext.ContextKeys.*;


public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    private static ScenarioContext scenarioContext;

    @Before("@API")
    public static void beforeAPI(Scenario scenario) {
        scenarioContext = ScenarioContext.getInstance();
        logger.info("---------------------Scenario '{}' started.---------------------", scenario.getName());
    }

    @Before("@UI")
    public static void beforeUI(Scenario scenario) {
        scenarioContext = ScenarioContext.getInstance();
        WebDriver webDriver = DriverManager.getDriver();
        scenarioContext.setContext(DRIVER, webDriver);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("---------------------Scenario '{}' started.---------------------", scenario.getName());
    }

    @AfterStep("@UI")
    public void afterUIStep(Scenario scenario) {
        ScreenShotUtil.takeScreenShot(scenario);
    }


    @After("@UserCreation")
    public void afterUserCreation() {
        DeleteUserActions deleteUserActions = new DeleteUserActions();
        deleteUserActions.aRequestToDeleteUserIsSent();
        logger.info("The user created for testing purposes was successfully deleted.");
    }

    @After("@UI")
    public void afterUI(Scenario scenario) {
        logger.info("---------------------Scenario '{}' ended.---------------------", scenario.getName());
        DriverManager.quitDriver();
        ScenarioContext.tearDown();
    }

    @After("@SuccessfullyUserRegistration")
    public void successfullyUserRegistration(Scenario scenario) {
        LoginUserActions loginUserActions = new LoginUserActions();
        DeleteUserActions deleteUserActions = new DeleteUserActions();
        String email = scenarioContext.getContext(EMAIL, String.class);
        String password = scenarioContext.getContext(PASSWORD, String.class);
        loginUserActions.aRequestToLoginWithCredentialsIsSent(email, password);
        deleteUserActions.aRequestToDeleteUserIsSent();
        logger.info("The user created for testing purposes was successfully deleted.");
        logger.info("---------------------Scenario '{}' ended.---------------------", scenario.getName());
    }

    @After("@API")
    public void afterAPI(Scenario scenario) {
        logger.info("---------------------Scenario '{}' ended.---------------------", scenario.getName());
        ScenarioContext.tearDown();
    }
}