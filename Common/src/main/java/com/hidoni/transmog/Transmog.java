package com.hidoni.transmog;

import com.hidoni.transmog.config.Config;
import com.hidoni.transmog.registry.ModRegistries;

import java.nio.file.Files;
import java.nio.file.Path;

public class Transmog {
    public static void init() {
        loadConfig();
        ModRegistries.register();
    }

    private static void loadConfig() {
        Path configPath = Config.getConfigPath();
        if (Files.exists(configPath)) {
            Config.loadConfigFromFile();
        }
        Config.writeConfigToFile();
    }
}
