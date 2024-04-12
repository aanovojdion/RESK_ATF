package org.example.api.actions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.configurations.Specifications;
import org.example.configurations.context.ScenarioContext;
import org.example.utils.CustomException;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.example.configurations.context.ScenarioContext.ContextKeys.RESPONSE;

public class DeleteUserActions {
    private static final ScenarioContext scenarioContext = ScenarioContext.getInstance();

    CreateUserActions createUserActions = new CreateUserActions();
    LoginUserActions loginUserActions = new LoginUserActions();


    @Given("user with valid data is already created")
    public void userWithValidDataIsAlreadyCreated(Map<String, String> createUserData) {
        createUserActions.userIsUsingValidData(createUserData);
        createUserActions.aRequestToCreateANewUserIsSent();
    }

    @When("a request to delete user is sent")
    public void aRequestToDeleteUserIsSent() {
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
//        loginUserActions.aRequestToLoginIsSent();
//        scenarioContext.getContext(RESPONSE, Response.class);
        try {
            logger.info("Sending a login request");
            Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(200));
            Response response = given()
                    .body(user)
                    .when()
                    .post("/users/login")
                    .then().log().all()
                    .extract().response();
            scenarioContext.setContext(RESPONSE, response);
        } catch (Exception ex) {
            logger.error("Login failed", ex);
            throw new CustomException(ex.getMessage());
        }
    }
    }
}
