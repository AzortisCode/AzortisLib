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
