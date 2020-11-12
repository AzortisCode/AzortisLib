/*
 * An open source utilities library used for Azortis plugins.
 *     Copyright (C) 2019  Azortis
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.azortis.azortislib.experimental.translation.impl;

import com.azortis.azortislib.experimental.translation.Translation;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class YAMLTranslation implements Translation {
    private final YamlConfiguration configuration;
    private File file;

    public YAMLTranslation() {
        configuration = new YamlConfiguration();
    }

    @Override
    public void addMessage(String key, String message) {
        configuration.set(key, message);
    }

    @Override
    public String get(String key) {
        return configuration.getString(key);
    }

    @Override
    public void removeMessage(String key) {
        configuration.set(key, null);
    }

    @Override
    public Map<String, String> getKeyMessageMap() {
        return null; // todo: do this
    }

    @Override
    public String getOrDefault(String key, String message) {
        return configuration.getString(key, message);
    }

    @Override
    public String getFileEnding() {
        return ".yml";
    }

    @Override
    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(File file) {
        this.file = file;
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(File file) {
        this.file = file;
        try {
            this.configuration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
