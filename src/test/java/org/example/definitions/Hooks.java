package org.example.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.context.Context;
import org.example.drivers.DriverManager;


public class Hooks extends Context {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void beforeScenario(Scenario scenario) {
        String scenarioName = scenario.getName();
        driver = DriverManager.getDriver();
        logger.info("Scenario '{}' started.", scenarioName);
    }

    @After
    public void afterScenario() {
        logger.info("Test passed");


        DriverManager.quitDriver();
    }
}