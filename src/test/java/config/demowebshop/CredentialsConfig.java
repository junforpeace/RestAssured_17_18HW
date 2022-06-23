package config.demowebshop;

import org.aeonbits.owner.Config;
import org.openqa.selenium.Capabilities;

import java.net.URI;
import java.net.URL;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:configs/credentials.properties"})
public interface CredentialsConfig extends Config{

    @Key("userLogin")
    String userLogin();

    @Key("userPassword")
    String userPassword();

    @Key("authCookieName")
    String authCookieName();

    @Key("webUrl")
    String webUrl();

    @Key("apiUrl")
    String apiUrl();

    @Key("remoteUrl")
    String remoteUrl();

    @Key("browser.name")
    String browser();

    @Key("browser.version")
    String browserVersion();

    @Key("browser.size")
    String browserSize();

    @Key("remoteWebDriver")
    String remoteWebDriver();
    @Key("selenoid_login")
    String selenoid_login();
    @Key("selenoid_password")
    String selenoid_password();

}
