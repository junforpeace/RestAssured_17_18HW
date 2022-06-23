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
    String demoLogin = config.userLogin();
    String demoPassword = config.userPassword();
    String authCookieName = config.authCookieName();

    @Test
    @DisplayName("Successful authorization to some demowebshop (UI)")
    void loginTest() {
        step("Open login page", () ->
                open("/login"));

        step("Fill login form", () -> {
            $("#Email").setValue(demoLogin);
            $("#Password").setValue(demoPassword)
                    .pressEnter();
        });

        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(demoLogin)));
    }

    @Test
    @DisplayName("Successful authorization to some demowebshop (API + UI) with allure listener")
    void loginWithApiAndAllureListenerTest() {
        step("Get cookie by api and set it to browser", () -> {
            String authCookieValue = given()
                    .filter(new AllureRestAssured())
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", demoLogin)
                    .formParam("Password", demoPassword)
                    .log().all()
                    .when()
                    .post("/login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract().cookie(authCookieName);

            step("Open minimal content, because cookie can be set when site is opened", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));
            step("Set cookie to to browser", () -> {
                Cookie authCookie = new Cookie(authCookieName, authCookieValue);
                WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
            });
        });

        step("Open main page", () ->
                open(""));
        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(demoLogin)));
    }

    @Test
    @Tag("demowebshop")
    @DisplayName("Login with API+UI, CustomListener")
    void loginApiCustomListenerTest() {
        step("Get cookie for authorization", () -> {
            String authCookie = given()
                    .filter(withCustomTemplates())
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", demoLogin)
                    .formParam("Password", demoPassword)
                    .log().all()
                    .when()
                    .post("/login")
                    .then()
                    .log().body()
                    .statusCode(302)
                    .extract().cookie(authCookieName);
            step("Open minimum content for set up cookie", () ->
                    open("/Themes/DefaultClean/Content/images/logo.png"));
            step("Set up cookie", () -> {
                Cookie ck = new Cookie(authCookieName, authCookie);
                WebDriverRunner.getWebDriver().manage().addCookie(ck);
            });
            step("Open main page", () ->
                    open(""));
            step("Verify successful authorization", () ->
                    $(".account").shouldHave(text(demoLogin)));
        });

    }

}
