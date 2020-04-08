package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigReader {

    private static Config config;

    private static void readConfig() {
        if (config == null) {
            config = ConfigFactory.parseResources("default.conf");
        }
    }

    public static String getUrl() {
        readConfig();
        return config.getString("conf.url");
    }

    public static String getHeader(){
        readConfig();
        return config.getString("conf.header-name");
    }

    public static String getKey() {
        readConfig();
        return config.getString("conf.key");
    }

    public static String getBlockedKey() {
        readConfig();
        return config.getString("conf.blocked-key");
    }
}
