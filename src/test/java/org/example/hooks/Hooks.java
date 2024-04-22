package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.example.api.actions.DeleteUserActions;
import org.example.api.actions.LoginUserActions;
import org.example.configurations.api_configs.EndPoints;
import org.example.configurations.context.ScenarioContext;
import org.example.configurations.drivers.DriverManager;
import org.example.utils.ScreenShotUtil;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.example.configurations.context.ScenarioContext.ObjectKeys.EMAIL;
import static org.example.configurations.context.ScenarioContext.ObjectKeys.PASSWORD;


public class Hooks {

    private static ScenarioContext scenarioContext;

    @Before("@API")
    public static void beforeAPI(Scenario scenario) {
        scenarioContext = ScenarioContext.getInstance();
        String scenarioName = scenario.getName().trim().replaceAll(" ", "_");
        ThreadContext.put("scenarioName", scenarioName);
        Configurator.reconfigure();
        ScenarioContext.tearDown();
        LogManager.getLogger().info("---------------------Scenario '{}' started.---------------------", scenario.getName());
    }

    @Before("@UI")
    public static void beforeUI(Scenario scenario) {
        String scenarioName = scenario.getName().trim().replaceAll(" ", "_");
        ThreadContext.put("scenarioName", scenarioName);
        Configurator.reconfigure();
        scenarioContext = ScenarioContext.getInstance();
        WebDriver webDriver = DriverManager.getDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ScenarioContext.tearDown();
        LogManager.getLogger().info("---------------------Scenario '{}' started.---------------------", scenario.getName());
    }

    @AfterStep("@UI")
    public void afterUIStep(Scenario scenario) {
        ScreenShotUtil.takeScreenShot(scenario);
    }


    @After("@UserCreationAPI")
    public void afterUserCreation() {
        DeleteUserActions deleteUserActions = new DeleteUserActions();
        deleteUserActions.aRequestToDeleteUserIsSent(EndPoints.DELETE_USER);
        LogManager.getLogger().info("The user created for testing purposes was successfully deleted.");
    }

    @After("@UI")
    public void afterUI(Scenario scenario) {
        LogManager.getLogger().info("---------------------Scenario '{}' ended.---------------------", scenario.getName());
        DriverManager.quitDriver();
    }

    @After("@SuccessfullyUserRegistration")
    public void successfullyUserRegistration(Scenario scenario) {
        LoginUserActions loginUserActions = new LoginUserActions();
        DeleteUserActions deleteUserActions = new DeleteUserActions();
        Map<String, String> credentials = Map.of("email", scenarioContext.getContext(EMAIL, String.class), "password", scenarioContext.getContext(PASSWORD, String.class));
        loginUserActions.userIsUsingValidCredentials(credentials);
        loginUserActions.aRequestToLoginIsSent(EndPoints.LOGIN_USER);
        deleteUserActions.aRequestToDeleteUserIsSent(EndPoints.DELETE_USER);
        LogManager.getLogger().info("The user created for testing purposes was successfully deleted.");
    }

    @After("@API")
    public void afterAPI(Scenario scenario) {
        LogManager.getLogger().info("---------------------Scenario '{}' ended.---------------------", scenario.getName());
    }
}