package controllers;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class UserController {
    RequestSpecification requestSpecification;
    private static final String BASE_URI = "https://petstore.swagger.io/v2/";
    private static final String USER_ENDPOINT = "user";

    public UserController() {
        this.requestSpecification = given()
                .accept(JSON)
                .contentType(JSON)
                .baseUri(BASE_URI);
    }

    public Response createUser(User user) {
        return given(this.requestSpecification)
                .body(user)
                .when()
                .post(USER_ENDPOINT)
                .andReturn();
    }

    public Response updateUser(User user) {
        return given(this.requestSpecification)
                .body(user)
                .when()
                .put(USER_ENDPOINT + "/" + user.getUserName())
                .andReturn();
    }

    public Response getUserByUserName(String userName) {
        return given(this.requestSpecification)
                .when()
                .get(USER_ENDPOINT + "/" + userName)
                .andReturn();
    }

    public Response deleteUserByUserName(String userName) {
        return given(this.requestSpecification)
                .when()
                .delete(USER_ENDPOINT + "/" + userName)
                .andReturn();
    }
}
