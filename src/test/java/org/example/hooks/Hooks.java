package org.example.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.configurations.context.ScenarioContext;
import org.example.configurations.drivers.DriverManager;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static org.example.configurations.context.ScenarioContext.ContextKeys.DRIVER;


public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);
    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    @Before
    public void beforeScenario(Scenario scenario) {
        String scenarioName = scenario.getName();
        WebDriver webDriver = DriverManager.getDriver();
        scenarioContext.setContext(DRIVER, webDriver);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger.info("Scenario '{}' started.", scenarioName);
    }

    @After
    public void afterScenario() {
        DriverManager.quitDriver();
    }
}