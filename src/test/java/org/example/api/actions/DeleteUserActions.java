package org.example.api.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.api.dtos.requests.UserData;
import org.example.configurations.Specifications;
import org.example.configurations.context.ScenarioContext;
import org.example.utils.CustomException;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.configurations.context.ScenarioContext.ContextKeys.RESPONSE;
import static org.example.configurations.context.ScenarioContext.ContextKeys.USERDATA;

public class DeleteUserActions {
    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();
    private static final Logger logger = LogManager.getLogger(DeleteUserActions.class);


    CreateUserActions createUserActions = new CreateUserActions();
//    LoginUserActions loginUserActions = new LoginUserActions();


    @Given("user with valid data is already created")
    public void userWithValidDataIsAlreadyCreated(Map<String, String> createUserData) {
        createUserActions.userIsUsingValidData(createUserData);
        createUserActions.aRequestToCreateANewUserIsSent();
    }

    @When("a request to delete user is sent")
    public void aRequestToDeleteUserIsSent() {
        logger.info("Sending a request to delete the current user.");
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(200));
        given().header("Authorization", scenarioContext
                        .getContext(RESPONSE, Response.class)
                        .jsonPath()
                        .getString("token"))
                .when()
                .delete("/users/me")
                .then()
                .log().all()
                .extract().response();
    }

    @Then("the user has successfully deleted")
    public void theUserHasSuccessfullyDeleted() {
        scenarioContext.getContext(RESPONSE, Response.class);
        try {
            logger.info("Sending a login request");
            Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(401));
            Response response = given()
                    .body(scenarioContext.getContext(USERDATA, UserData.class))
                    .when()
                    .post("/users/login")
                    .then().log().all()
                    .extract().response();
            logger.info("The user is not logged in, which indicates a successful deletion.");
        } catch (Exception ex) {
            logger.error("An error occurred during the login attempt: {}", ex.getMessage());
            throw new CustomException(ex.getMessage());
        }
    }
}