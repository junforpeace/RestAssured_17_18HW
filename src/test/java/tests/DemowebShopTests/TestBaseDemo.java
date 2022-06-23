package tests.DemowebShopTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.demowebshop.CredentialsConfig;
import helpers.AllureAttachments;
import io.github.bonigarcia.wdm.config.Config;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static io.qameta.allure.Allure.step;


public class TestBaseDemo {

    final static CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class, System.getProperties());

     @BeforeAll
    static void configureBaseUrl() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.baseUrl = config.webUrl();
        RestAssured.baseURI = config.apiUrl();

        String remoteWebDriver = config.remoteWebDriver();

        if (remoteWebDriver != null) {
            step("Remote web driver setup", () -> {
                Configuration.remote = "https://" + config.selenoid_login() + ":" + config.selenoid_password() + "@selenoid.autotests.cloud/wd/hub";
                
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                Configuration.browserCapabilities = capabilities;

            });
        }
    }
    @AfterEach
    void afterEach() {
        AllureAttachments.screenshotAs("Last screenshot");
        AllureAttachments.pageSource();
        AllureAttachments.browserConsoleLogs();
        AllureAttachments.addVideo();
        closeWebDriver();

    }
}
