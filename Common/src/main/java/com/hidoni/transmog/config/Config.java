package com.hidoni.transmog.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hidoni.transmog.Constants;
import com.hidoni.transmog.platform.Services;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    public static TransmogRenderOption renderOption = TransmogRenderOption.IN_WORLD;
    public static TooltipDetailLevel tooltipDetailLevel = TooltipDetailLevel.FULL;
    public static int pvpDisableDuration = 0;
    @SuppressWarnings("UnnecessaryModifier")
    private transient static final Gson GSON = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).serializeNulls().serializeSpecialFloatingPointValues().setPrettyPrinting().setLenient().create();


    public static Path getConfigPath() {
        return Services.PLATFORM.getConfigDirectory().resolve(Constants.MOD_ID + ".json");
    }

    public static void loadConfigFromFile() {
        Path configPath = getConfigPath();
        try {
            GSON.fromJson(Files.readString(configPath), Config.class);
        } catch (IOException e) {
            Constants.LOG.warn("Failed to read config file from " + configPath, e);
        }
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void writeConfigToFile() {
        Path configPath = getConfigPath();
        try {
            String jsonString = GSON.toJson(new Config());
            Files.writeString(configPath, jsonString);
        } catch (IOException e) {
            Constants.LOG.warn("Failed to write config file to " + configPath, e);
        }
    }
}
