package org.example.api.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.dtos.requests.UserData;
import org.example.api.dtos.responses.SuccessLoginResponse;
import org.example.api.dtos.responses.SuccessNewUserCreate;
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
    private static final Logger logger = LogManager.getLogger(LoginUserActions.class);


    //    LoginUserData user;
    SuccessLoginResponse successLoginResponse;

    SuccessNewUserCreate successNewUserCreate = new SuccessNewUserCreate();


    @Given("user is using valid credentials")
    public void userIsUsingValidCredentials(Map<String, String> createUserData) {
        logger.info("Preparing the user data");
        scenarioContext.setContext(USERDATA, new UserData(createUserData));
        logger.info("User data was prepared");
    }

    @When("a request to login is sent to {endPoint} endPoint")
    public void aRequestToLoginIsSent(EndPoints endPoint) {
        logger.info("Sending a login request");
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
        logger.info("Verifying that user is logged in");
        Response response = scenarioContext.getContext(RESPONSE, Response.class);
        successLoginResponse = response.as(SuccessLoginResponse.class);
        assertNotNull(successLoginResponse.getToken());
        assertEquals(response.getStatusCode(), 200);
        assertEquals(successLoginResponse.getUser().getEmail(), scenarioContext.getContext(USERDATA, UserData.class).getEmail());
        logger.info("User is successfully logged in");
    }
}
