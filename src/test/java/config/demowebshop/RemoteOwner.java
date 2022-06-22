package config.demowebshop;

import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:configs/remote.properties"
})
public interface RemoteOwner extends Config {
    @Key("remoteUrl")
    String remoteUrl();

}
