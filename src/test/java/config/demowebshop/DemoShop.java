package config.demowebshop;

import org.aeonbits.owner.ConfigFactory;

public class DemoShop {
    public static DemoShopConfig config = ConfigFactory.create(DemoShopConfig.class, System.getProperties());
}