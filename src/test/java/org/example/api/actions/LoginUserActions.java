package org.example.api.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.example.api.dtos.requests.UserData;
import org.example.api.dtos.responses.SuccessLoginResponse;
import org.example.configurations.api_configs.EndPoints;
import org.example.configurations.api_configs.Specifications;
import org.example.configurations.context.ScenarioContext;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.configurations.context.ScenarioContext.ObjectKeys.RESPONSE;
import static org.example.configurations.context.ScenarioContext.ObjectKeys.USERDATA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginUserActions {
    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    SuccessLoginResponse successLoginResponse;

    @Given("user is using valid credentials")
    public void userIsUsingValidCredentials(Map<String, String> createUserData) {
        LogManager.getLogger().info("Preparing the user data");
        scenarioContext.setContext(USERDATA, new UserData(createUserData));
        LogManager.getLogger().info("User data was prepared");
    }

    @When("a request to login is sent to {endPoint} endPoint")
    public void aRequestToLoginIsSent(EndPoints endPoint) {
        LogManager.getLogger().info("Sending a login request");
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(200));
        Response response = given()
                .body(scenarioContext.getContext(USERDATA, UserData.class))
                .when()
                .post(endPoint.getValue())
                .then().log().all()
                .extract().response();
        scenarioContext.setContext(RESPONSE, response);
    }

    @Then("response that user successfully logged in is received")
    public void responseThatUserSuccessfullyLoggedInIsReceived() {
        LogManager.getLogger().info("Verifying that user is logged in");
        Response response = scenarioContext.getContext(RESPONSE, Response.class);
        successLoginResponse = response.as(SuccessLoginResponse.class);
        assertNotNull(successLoginResponse.getToken());
        assertEquals(response.getStatusCode(), 200);
        assertEquals(successLoginResponse.getUser().getEmail(), scenarioContext.getContext(USERDATA, UserData.class).getEmail());
        LogManager.getLogger().info("User is successfully logged in");
    }
}