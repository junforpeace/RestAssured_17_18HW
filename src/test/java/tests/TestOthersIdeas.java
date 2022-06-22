package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class TestOthersIdeas {


    @Test
    @DisplayName("Проверка структуры массива data для элемента списка ресурсов")
    public  void resourceListTest()
    {
        String endpoint = "https://reqres.in/api/unknown";

        given()
                .when()
                .log().uri()
                .log().body()
                .get(endpoint)
                .then()
                .statusCode(200)
                .log().status()
                .log().body()
                .body("data[0]", hasKey("id"))
                .body("data[0]", hasKey("year"))
                .body("data[0]", hasKey("name"))
                .body("data[0]", hasKey("color"))
                .body("data[0]", hasKey("pantone_value"));
    }
}
