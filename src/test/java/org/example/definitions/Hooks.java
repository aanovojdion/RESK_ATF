package org.example.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.example.drivers.DriverManager;

public class Hooks {

    @Before
    public void beforeScenario() {
        DriverManager.getDriver();
    }

    @After
    public void afterScenario() {
        DriverManager.quitDriver();
    }
}