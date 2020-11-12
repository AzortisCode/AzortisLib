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

import java.io.File;
import java.util.Map;

public class PropertiesTranslation implements Translation {
    @Override
    public void addMessage(String key, String message) {

    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void save(File file) {

    }

    @Override
    public void removeMessage(String key) {

    }

    @Override
    public Map<String, String> getKeyMessageMap() {
        return null;
    }

    @Override
    public String getOrDefault(String key, String message) {
        return null;
    }

    @Override
    public String getFileEnding() {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void reload() {

    }

    @Override
    public void load(File file) {

    }
}
