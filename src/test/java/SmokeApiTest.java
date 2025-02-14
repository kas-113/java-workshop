import controllers.UserController;
import io.restassured.response.Response;
import models.AddUserResponse;
import models.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static testdata.TestData.DEFAULT_USER;
import static testdata.TestData.INVALID_USER;

public class SmokeApiTest {
    UserController userController = new UserController();

    @Test
    void simpleTest() {
        String body = """ 
                {
                "id":0,
                "username":"string",
                "firstName":"string",
                "lastName":"string",
                "email":"string",
                "password":"string",
                "phone":"string",
                "userStatus":0
                }
                """;

        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .baseUri("https://petstore.swagger.io/v2/")
                .when()
                .body(body)
                .post("user")
                .andReturn();
        int actualCode = response.getStatusCode();

        Assertions.assertEquals(200, actualCode);
    }

    @Test
    void checkUserResponseBody() {
        String baseUrl = "https://petstore.swagger.io/v2/";
        String body = """
                {
                  "id": 0,
                  "username": "string",
                  "firstName": "string",
                  "lastName": "string",
                  "email": "string",
                  "password": "string",
                  "phone": "string",
                  "userStatus": 0
                }""";

        given()
                .baseUri(baseUrl)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body).
                when()
                .post("user")
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", notNullValue(String.class));
    }

    @Test
    void createUserControllerTest() {
        Response response = userController.createUser(DEFAULT_USER);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }

    @Test
    void createInvalidUserControllerTest() {
        Response response = userController.createUser(INVALID_USER);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertEquals("0", createdUserResponse.getMessage());
    }

    static Stream<User> users() {
        return Stream.of(DEFAULT_USER, INVALID_USER);
    }

    @ParameterizedTest
    @MethodSource("users")
    void createUserParameterizedTest(User user) {
        Response response = userController.createUser(user);
        AddUserResponse createdUserResponse = response.as(AddUserResponse.class);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, createdUserResponse.getCode());
        Assertions.assertEquals("unknown", createdUserResponse.getType());
        Assertions.assertFalse(createdUserResponse.getMessage().isEmpty());
    }
}
