package config.demowebshop;

import org.aeonbits.owner.Config;

import java.net.URL;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:configs/demowebshop/demoshop.properties",
        "classpath:configs/local.properties"
})
public interface DemoShopConfig extends Config {
    @Key("webUrl")
    String webUrl();

    @Key("apiUrl")
    String apiUrl();

    @Key("userLogin")
    String userLogin();

    @Key("userPassword")
    String userPassword();

    @Key("authCookieName")
    String authCookieName();
}
