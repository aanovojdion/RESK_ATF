//package org.example.definitions;
//
//import io.cucumber.java.en.When;
//import io.restassured.RestAssured;
//
//import java.util.HashMap;
//import java.util.logging.LogManager;
//import java.util.logging.Logger;
//
//public class StepDefApi {
//
//    private static final Logger logger = LogManager.getLogManager().getLogger(StepDefApi.class);
//
//    @When("User is on Home page")
//    public void UserIsOnHomePage(String baseURL) {
//        String homePageUrl =
//                RestAssured.baseURI = baseURL;
//        logger.info("The base URL is set: " + baseURL);
//    }
//
//    @When("user submits registration form with valid data")
//    public void userSubmitsRegistrationFormWithValidData(String endpoint) {
//        logger.info("POST request is sent to the Server");
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put("email", "black.jack@mail.md");
//        map.put("password", "Test123");
//        response = given()
//    }
//}
