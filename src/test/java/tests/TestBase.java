package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.AllureAttachments;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.open;
@ExtendWith({AllureJunit5.class})
public class TestBase {
   String baseUrl = "https://reqres.in",
    createUrl = "/api/users",
    usersListUrl = "/api/users?page=2",
    userPutUrl = "/api/users/2",
    deleteUserUrl = "/api/users/2",
    registerUrl = "/api/register",
    notLogin = "/api/login",
    name = "morpheus",
    job = "leader",
    bodyForCreate = "{\"name\": \"morpheus\", " + "\"job\": \"leader\"}",
    bodyForDelete = "{\"email\": \"eve.holt@reqres.in\", " + "\"password\": \"pistol\"}",
    bodyForNotLogin = "{\"email\": \"peter@klaven\"}",
    userPutJob = "zion resident",
    errorMessage = "Missing password",
    token = "QpwL5tke4Pnpja7X4",
    bodyUpdate = "{\"nam" + "e\": \"morpheus\", " + "\"job\": \"zion resident\"}";

        @BeforeEach
        public void beforeEach() {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        }

        @AfterEach
        public void afterEach() {
            AllureAttachments.screenshotAs("Last screenshot");
            AllureAttachments.pageSource();
            AllureAttachments.browserConsoleLogs();

            Selenide.closeWebDriver();

        }

}


