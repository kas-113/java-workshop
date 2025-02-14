package controllers;

import config.TestPropertiesConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.User;
import org.aeonbits.owner.ConfigFactory;

import static constants.CommonConstants.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static testdata.TestData.DEFAULT_USER;

public class UserController {
    RequestSpecification requestSpecification;
    private static final String USER_ENDPOINT = "user";
    TestPropertiesConfig configProperties = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());

    public UserController() {
        this.requestSpecification = given()
                .accept(JSON)
                .contentType(JSON)
                .baseUri(configProperties.getApiBaseUrl());
    }

    public Response createDefaultUser() {
        return given(this.requestSpecification)
                .body(DEFAULT_USER)
                .when()
                .post(USER_ENDPOINT)
                .andReturn();
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
                .put(USER_ENDPOINT + "/" + user.getUsername())
                .andReturn();
    }

    public Response getUserByUsername(String username) {
        return given(this.requestSpecification)
                .when()
                .get(USER_ENDPOINT + "/" + username)
                .andReturn();
    }

    public Response deleteUserByUsername(String userName) {
        return given(this.requestSpecification)
                .when()
                .delete(USER_ENDPOINT + "/" + userName)
                .andReturn();
    }
}
