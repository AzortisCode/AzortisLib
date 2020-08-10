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

package com.azortis.azortislib.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * A class used to get the latest version of this plugin from spigot using SpiGet
 */
public class VersionUtil {
    private final String version;
    private String downloadedVersion;

    public VersionUtil(JavaPlugin plugin) {
        version = plugin.getDescription().getVersion();
    }

    public Status check(int resourceID) {
        try {
            URLConnection connection = new URL("https://api.spiget.org/v2/resources/" + resourceID + "/versions/latest").openConnection();
            JsonObject object = (JsonObject) new JsonParser().parse(new InputStreamReader(connection.getInputStream()));
            downloadedVersion = object.get("name").getAsString();
            String[] dVer = downloadedVersion.split("\\.");
            String[] ver = version.split("\\.");
            FormatUtil.equalizeStringArray(ver, dVer);
            String sVer = ver[0] + ver[1] + ver[2];
            String stringver = dVer[0] + dVer[1] + dVer[2];
            int result = sVer.compareTo(stringver);
            if (result == 0) {
                return Status.NEW;
            } else if (result < 0) {
                return Status.OLD;
            } else {
                return Status.UNRELEASED;
            }

        } catch (IOException e) {
            return Status.UNKNOWN;
        }
    }

    public String getDownloadedVersion() {
        return downloadedVersion;
    }

    public enum Status {
        OLD, NEW, UNRELEASED, UNKNOWN
    }
}
