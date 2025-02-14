package controllers;

import io.restassured.specification.RequestSpecification;

import static constants.CommonConstants.BASE_URI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class StoreController {
    RequestSpecification requestSpecification;
    private static final String USER_ENDPOINT = "store";

    public StoreController() {
        this.requestSpecification = given()
                .accept(JSON)
                .contentType(JSON)
                .baseUri(BASE_URI);
    }

    //implement
}

