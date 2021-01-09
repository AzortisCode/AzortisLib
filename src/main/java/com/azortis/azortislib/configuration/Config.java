/*
 * MIT License
 *
 * Copyright (c) 2021 Azortis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.azortis.azortislib.configuration;

import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * A configuration file class which loads a JSON configuration file.
 */
@SuppressWarnings("all")
public class Config<T> {
    private final Gson gson;
    private final File configurationFile;
    private T configuration;

    /**
     * @param name the name/path of the configuration file within the plugin's data folder.
     * @param gson The gson for the configuration file to use to load in the configuration data.
     * @param plugin The plugin to use for the data folder.
     * @param defaults The default values to use if no file is found or exists.
     */
    public Config(String name, Gson gson, JavaPlugin plugin, T defaults) {
        this.gson = gson;
        configurationFile = new File(plugin.getDataFolder(), name.endsWith(".json") ? name : name + ".json");
        try {
            if (!plugin.getDataFolder().exists()) plugin.getDataFolder().mkdirs();
            if (!configurationFile.exists()) {
                configurationFile.mkdirs();
                configurationFile.createNewFile();
                configuration = defaults;
                saveConfig();
            } else
                configuration = (T) gson.fromJson(new FileReader(configurationFile), defaults.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @return The configuration file
     */
    public File getConfigurationFile() {
        return configurationFile;
    }

    /**
     *
     * @return The configuration data
     */
    public T getConfiguration() {
        return configuration;
    }

    /**
     * Saves the configuration file
     */
    public void saveConfig() {
        try {
            final String json = gson.toJson(configuration);
            configurationFile.delete();
            Files.write(configurationFile.toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reloads the configuration file
     */
    public void reloadConfig() {
        try {
            configuration = (T) gson.fromJson(new FileReader(configurationFile), configuration.getClass());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
