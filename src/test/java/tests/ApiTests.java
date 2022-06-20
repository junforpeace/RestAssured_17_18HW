package tests;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.Is.is;

public class ApiTests extends TestBase {

    @Test
    void createUserTest() {
        given()
                .log().uri()
                .log().body()
                .body(bodyForCreate)
                .contentType(JSON)
                .when()
                .post(baseUrl + createUrl)
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is(name))
                .body("job", is(job));
    }

    @Test
    void updateUserTest() {
        given()
                .log().body()
                .body(bodyUpdate)
                .contentType(JSON)
                .when()
                .put(baseUrl + userPutUrl)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is(userPutJob));
    }

    @Test
    void listUsersTest() {
        given()
                .when()
                .get(baseUrl + usersListUrl)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));

    }

    @Test
    void registerUserTest() {
        given()
                .when()
                .delete(baseUrl + deleteUserUrl)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }

    @Test
    void deleteUsersTest() {
        given()
                .log().body()
                .body(bodyForDelete)
                .contentType(JSON)
                .when()
                .post(baseUrl + registerUrl)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is(token))
                .body("id", is(4));
    }

    @Test
    void unsuccessfulLoginTest() {
        given()
                .log().body()
                .log().uri()
                .body(bodyForNotLogin)
                .when()
                .get(baseUrl + notLogin)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is(errorMessage));
    }
}