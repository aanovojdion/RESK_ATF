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
import org.example.configurations.Specifications;
import org.example.configurations.context.ScenarioContext;
import org.example.utils.CustomException;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.configurations.context.ScenarioContext.ContextKeys.RESPONSE;
import static org.example.configurations.context.ScenarioContext.ContextKeys.USERDATA;
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

    @When("a request to login is sent")
    public void aRequestToLoginIsSent() {
        try {
            logger.info("Sending a login request");
            Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(200));
            Response response = given()
                    .body(scenarioContext.getContext(USERDATA, UserData.class))
                    .when()
                    .post("/users/login")
                    .then().log().all()
                    .extract().response();
            scenarioContext.setContext(RESPONSE, response);
        } catch (Exception ex) {
            logger.error("Login failed", ex);
            throw new CustomException(ex.getMessage());
        }//TODO: Дописать Exception
    }

    @When("a request to login with credentials is sent")
    public void aRequestToLoginWithCredentialsIsSent(String email, String password) {
        UserData userData = new UserData(email, password);
        try {
            logger.info("Sending a login request");
            Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(200));
            Response response = given()
                    .body(userData)
                    .when()
                    .post("/users/login")
                    .then().log().all()
                    .extract().response();
            scenarioContext.setContext(RESPONSE, response);
        } catch (Exception ex) {
            logger.error("Login failed", ex);
        }
    }

    @Then("response that user successfully logged in is received")
    public void responseThatUserSuccessfullyLoggedInIsReceived() {
        try {
            logger.info("Verifying that user is logged in");
            Response response = scenarioContext.getContext(RESPONSE, Response.class);
            successLoginResponse = response.as(SuccessLoginResponse.class);
            assertNotNull(successLoginResponse.getToken());
            assertEquals(response.getStatusCode(), 200);
            assertEquals(successLoginResponse.getUser().getEmail(), scenarioContext.getContext(USERDATA, UserData.class).getEmail());
            logger.info("User is successfully logged in");
        } catch (Exception ex) {
            logger.error("Login verification failed", ex);
            throw new CustomException(ex.getMessage());
        }
    }
}
