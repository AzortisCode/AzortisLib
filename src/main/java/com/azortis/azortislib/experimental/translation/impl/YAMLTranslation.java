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
