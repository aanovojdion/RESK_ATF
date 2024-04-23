package org.example.api.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
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

    SuccessNewUserCreate successNewUserCreate = new SuccessNewUserCreate();

    @Given("user is using valid data")
    public void userIsUsingValidData(Map<String, String> createUserData) {
        LogManager.getLogger().info("Preparing the user data");
        scenarioContext.setContext(USERDATA, new UserData(createUserData));
        LogManager.getLogger().info("User data was prepared");
    }

    @When("a request to create a new user is sent to {endPoint} endPoint")
    public void aRequestToCreateANewUserIsSent(EndPoints endPoint) {
        try {
            LogManager.getLogger().info("Sending a request to create a new user");
            Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(201));
            Response response = given()
                    .body(scenarioContext.getContext(USERDATA, UserData.class))
                    .when()
                    .post(endPoint.getValue())
                    .then()
                    .log().all()
                    .extract().response();
            scenarioContext.setContext(RESPONSE, response);
            LogManager.getLogger().info("A request to create a new user has been sent");
        } catch (Exception ex) {
            LogManager.getLogger().info("The request to create a new user failed.", ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Then("the user has successfully created")
    public void theUserHasSuccessfullyCreated() {
        try {
            LogManager.getLogger().info("Verifying that the token was successfully received");
            Response response = scenarioContext.getContext(RESPONSE, Response.class);
            JsonPath jsonpath = response.jsonPath();
            String token = jsonpath.getString("token");
            assertNotNull("token should not be null", token);
            successNewUserCreate.setToken(token);
            LogManager.getLogger().info("The token has been received");
            LogManager.getLogger().info("The new user has been successfully created");
        } catch (Exception ex) {
            LogManager.getLogger().info("The token has not been received");
            LogManager.getLogger().info("New user creation failed", ex);
            throw new RuntimeException(ex.getMessage());
        }
    }
}