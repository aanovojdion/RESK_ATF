package org.example.api.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.example.api.dtos.requests.UserData;
import org.example.configurations.api_configs.EndPoints;
import org.example.configurations.api_configs.Specifications;
import org.example.configurations.context.ScenarioContext;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.configurations.context.ScenarioContext.ObjectKeys.RESPONSE;
import static org.example.configurations.context.ScenarioContext.ObjectKeys.USERDATA;

public class DeleteUserActions {
    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    CreateUserActions createUserActions = new CreateUserActions();

    @Given("user with valid data is already created")
    public void userWithValidDataIsAlreadyCreated(Map<String, String> createUserData) {
        createUserActions.userIsUsingValidData(createUserData);
        createUserActions.aRequestToCreateANewUserIsSent(EndPoints.CREATE_USER);
    }

    @When("a request to delete user is sent to {endPoint} endPoint")
    public void aRequestToDeleteUserIsSent(EndPoints endPoint) {
        LogManager.getLogger().info("Sending a request to delete the current user.");
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(200));
        given().header("Authorization", scenarioContext
                        .getContext(RESPONSE, Response.class)
                        .jsonPath()
                        .getString("token"))
                .when()
                .delete(endPoint.getValue())
                .then()
                .log().all()
                .extract().response();
    }

    @Then("the user has successfully deleted")
    public void theUserHasSuccessfullyDeleted() {
        scenarioContext.getContext(RESPONSE, Response.class);
        try {
            LogManager.getLogger().info("Sending a login request");
            Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(401));
            Response response = given()
                    .body(scenarioContext.getContext(USERDATA, UserData.class))
                    .when()
                    .post("/users/login")
                    .then().log().all()
                    .extract().response();
            LogManager.getLogger().info("The user is not logged in, which indicates a successful deletion.");
        } catch (Exception ex) {
            LogManager.getLogger().info("An error occurred during the login attempt: {}", ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }
}