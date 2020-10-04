package com.azortis.azortislib.experimental.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("all")
public class ConfigManager {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final JavaPlugin plugin;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public <T> Config<T> loadConfig(String name, T defaults) {
        return new Config<T>(name, gson, plugin, defaults);
    }

    public Gson getGson() {
        return gson;
    }
}


