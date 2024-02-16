//package org.example.context;
//
//import org.example.drivers.DriverManager;
//import org.example.drivers.DriverType;
//import org.example.pages.AddContact;
//import org.example.pages.HomePage;
//import org.openqa.selenium.WebDriver;
//
//import java.io.IOException;
//
//import static org.example.config.PropertyLoader.getProperty;
//
//public class TestContext {
//    private WebDriver webDriver;
//    private HomePage homePage;
//    private AddContact addContact;
//    public ScenarioContext scenarioContext;
//
//    public TestContext() throws IOException {
//        this.webDriver = DriverManager.getManager(getProperty("browser");
//        this.homePage = new HomePage();
//        this.addContact = new AddContact(webDriver);
//        this.scenarioContext = new ScenarioContext();
//    }
//
//    public HomePage getHomePage() {
//        return homePage;
//    }
//
//    public AddContact getAddContact() {
//        return addContact;
//    }
//
//    public ScenarioContext getScenarioContext() {
//        return scenarioContext;
//    }
//}
