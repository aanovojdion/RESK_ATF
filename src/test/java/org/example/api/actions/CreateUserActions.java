package org.example.api.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.dtos.requests.CreateUserData;
import org.example.api.dtos.responses.SuccessNewUserCreate;
import org.example.configurations.Specifications;
import org.example.configurations.context.ScenarioContext;
import org.example.utils.CustomException;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.configurations.context.ScenarioContext.ContextKeys.RESPONSE;
import static org.example.configurations.context.ScenarioContext.ContextKeys.USERDATA;
import static org.junit.Assert.assertNotNull;

public class CreateUserActions {
    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private static final Logger logger = LogManager.getLogger(CreateUserActions.class);

    SuccessNewUserCreate successNewUserCreate = new SuccessNewUserCreate();

    @Given("user is using valid data")
    public void userIsUsingValidData(Map<String, String> createUserData) {
        logger.info("Preparing the user data");
        scenarioContext.setContext(USERDATA, new CreateUserData(createUserData));
        logger.info("User data was prepared");
    }

    @When("a request to create a new user is sent")
    public void aRequestToCreateANewUserIsSent() {
        try {
            logger.info("Sending a request to create a new user");
            Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(201));
            Response response = given()
                    .body(scenarioContext.getContext(USERDATA, CreateUserData.class))
                    .when()
                    .post("/users")
                    .then()
                    .log().all()
                    .extract().response();
            scenarioContext.setContext(RESPONSE, response);
            logger.info("The new user has been successfully created");
        } catch (Exception ex) {
            logger.error("New user creation failed", ex);
            throw new CustomException(ex.getMessage());
        }
    }

    @Then("the user has successfully created")
    public void theUserHasSuccessfullyCreated() {
        try {
            logger.info("Verifying that the token was successfully received");
            Response response = scenarioContext.getContext(RESPONSE, Response.class);
            JsonPath jsonpath = response.jsonPath();
            String token = jsonpath.getString("token");
            assertNotNull(token);
            successNewUserCreate.setToken(token);
            logger.info("The token has been received");
        } catch (Exception ex) {
            logger.error("The token has not been received");
        }
    }
}