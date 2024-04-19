package org.example.api.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.dtos.requests.UserData;
import org.example.api.dtos.responses.SuccessNewUserCreate;
import org.example.configurations.api_configs.EndPoints;
import org.example.configurations.api_configs.Specifications;
import org.example.configurations.context.ScenarioContext;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.configurations.context.ScenarioContext.ObjectKeys.RESPONSE;
import static org.example.configurations.context.ScenarioContext.ObjectKeys.USERDATA;
import static org.junit.Assert.assertNotNull;

public class CreateUserActions {
    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private static final Logger logger = LogManager.getLogger(CreateUserActions.class);

    SuccessNewUserCreate successNewUserCreate = new SuccessNewUserCreate();

    @Given("user is using valid data")
    public void userIsUsingValidData(Map<String, String> createUserData) {
        logger.info("Preparing the user data");
        scenarioContext.setContext(USERDATA, new UserData(createUserData));
        logger.info("User data was prepared");
    }

    @When("a request to create a new user is sent to {endPoint} endPoint")
    public void aRequestToCreateANewUserIsSent(EndPoints endPoint) {
        try {
            logger.info("Sending a request to create a new user");
            Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(201));
            Response response = given()
                    .body(scenarioContext.getContext(USERDATA, UserData.class))
                    .when()
                    .post(endPoint.getValue())
                    .then()
                    .log().all()
                    .extract().response();
            scenarioContext.setContext(RESPONSE, response);
            logger.info("A request to create a new user has been sent");
        } catch (Exception ex) {
            logger.error("The request to create a new user failed.", ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Then("the user has successfully created")
    public void theUserHasSuccessfullyCreated() {
        try {
            logger.info("Verifying that the token was successfully received");
            Response response = scenarioContext.getContext(RESPONSE, Response.class);
            JsonPath jsonpath = response.jsonPath();
            String token = jsonpath.getString("token");
            assertNotNull("token should not be null", token);
            successNewUserCreate.setToken(token);
            logger.info("The token has been received");
            logger.info("The new user has been successfully created");
        } catch (Exception ex) {
            logger.error("The token has not been received");
            logger.error("New user creation failed", ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}