//package org.example.api.actions;
//
//import io.restassured.response.Response;
//import org.example.api.dtos.requests.UserData;
//import org.example.api.dtos.responses.ContactResponse;
//import org.example.configurations.Specifications;
//import org.example.configurations.context.ScenarioContext;
//
//import java.util.List;
//
//import static io.restassured.RestAssured.given;
//import static org.example.configurations.context.ScenarioContext.ContextKeys.RESPONSE;
//import static org.example.configurations.context.ScenarioContext.ContextKeys.USERDATA;
//
//public class DeleteContactsActions {
//
//    static ScenarioContext scenarioContext = ScenarioContext.getInstance();
//    private static final String token = scenarioContext.getContext(RESPONSE, Response.class).jsonPath()
//            .getString("token");
//
//    private static String getToken() {
//        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpec(200));
//        Response response = given()
//                .body(scenarioContext.getContext(USERDATA, UserData.class))
//                .when()
//                .post("/users/login")
//                .then().log().all()
//                .extract().response();
//        scenarioContext.setContext(RESPONSE, response);
//    }
//
//    private static void getContacts() {
//        Response response = given().header("Authorization", token)
//                .when()
//                .get("/contacts")
//                .then()
//                .log().all()
//                .extract().response();
//        scenarioContext.setContext(RESPONSE, response);
//    }
//
//    private static List<String> getContactIds(Response response) {
//        List<ContactResponse> contacts = response
//                .jsonPath()
//                .getList("", ContactResponse.class);
//        return contacts.stream()
//                .map(ContactResponse::get_id)
//                .toList();
//    }
//
//    private static void deleteAllContacts(Response response, String token) {
//        for (String id : getContactIds(response)) {
//            given().header("Authorization", token)
//                    .when()
//                    .delete("/contacts/" + id)
//                    .then()
//                    .log().all()
//                    .extract().response();
//        }
//    }
//
//    public static void clearContactList() {
//        getContacts();
//        getContactIds(scenarioContext.getContext(RESPONSE, Response.class));
//        deleteAllContacts(scenarioContext.getContext(RESPONSE, Response.class), token);
//    }
//}
