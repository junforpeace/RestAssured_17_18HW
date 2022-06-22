package tests.DemowebShopTests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class DemoWebShopTest extends TestBaseDemo {

    @Test
    @Tag("demowebshop")
    @DisplayName("Login with UI")
    void loginTest() {
        step("Open login page", () ->
                open("/login"));

        step("Fill login form", () -> {
            $("#Email").setValue(config.userLogin());
            $("#Password").setValue(config.userPassword())
                    .pressEnter();
        });

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(config.userLogin())));
    }


    @Test
    @Tag("demowebshop")
    @DisplayName("Login with API+UI")
    void loginApiCustomListenerTest() {
        step("Get cookie for authorization", () -> {
            String authCookie = given()
                    .filter(withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", config.userLogin())
                    .formParam("Password", config.userPassword())
                    .log().all()
                    .when()
                    .post("/login")
                    .then()
                    .log().body()
                    .statusCode(302)
                    .extract().cookie(config.authCookieName());
            step("Open minimum content for set up cookie", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));
            step("Set up cookie", () -> {
                Cookie ck = new Cookie(config.authCookieName(), authCookie);
                WebDriverRunner.getWebDriver().manage().addCookie(ck);
            });
            step("Open main page", () ->
                    open(""));
            step("Verify successful authorization", () ->
                    $(".account").shouldHave(text(config.userLogin())));
        });

    }

}
