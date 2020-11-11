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

package com.azortis.azortislib.version;

import com.azortis.azortislib.utils.FormatUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * A class used to get the latest version of this plugin from spigot using the SpiGet API
 */
@SuppressWarnings("unused")
public class Updater {
    private final String version;
    private final String userAgent;
    private final int resource;
    private String latestVersion;

    /**
     *
     * @param version the plugin's version.
     * @param userAgent the user agent to send alongside the resource request.
     * @param resourceID the plugin's spigot resource id.
     */
    public Updater(String version, String userAgent, int resourceID) {
        this.resource = resourceID;
        this.version = version;
        this.userAgent = userAgent;
    }

    /**
     * Compares the plugin's current version to the latest version SpiGet can find.
     *
     * @return {@link Status} the status of the plugin's current version compared to the latest version.
     */
    public Status checkVersion() {
        try {
            URLConnection connection = new URL("https://api.spiget.org/v2/resources/" + this.resource + "/versions/latest").openConnection();
            connection.addRequestProperty("User-Agent", this.userAgent);
            JsonObject object = (JsonObject) new JsonParser().parse(new InputStreamReader(connection.getInputStream()));
            this.latestVersion = object.get("name").getAsString();

            String[] dVer = this.latestVersion.split("\\.");
            String[] ver = this.version.split("\\.");
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

        } catch (IOException | NumberFormatException e) {
            return Status.UNKNOWN;
        }
    }

    /**
     * Gets the latest version currently known from SpiGet.
     * Value is only updated when the method checkVersion is run.
     *
     * @return The latest version of the plugin in string form.
     */
    public String getLatestVersion() {
        return latestVersion;
    }

    /**
     * An enum which represents the status of the current plugin when compared to the latest version known.
     */
    public enum Status {
        /**
         * The current plugin version is older than the latest released version on spigotmc.
         */
        OLD,
        /**
         * The current plugin version is the latest version possible and is up to date.
         */
        NEW,
        /**
         * The current plugin version is a dev build and/or hasn't been released to the spigotmc page.
         */
        UNRELEASED,
        /**
         * The current plugin version is unknown when compared in relation to the known versions.
         * This is only returned when the version contains non-numerical digits or if an error occurred.
         */
        UNKNOWN
    }
}
