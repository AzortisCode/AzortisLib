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

import org.bukkit.ChatColor;

public class FormatUtil {
    public static String getSeparator(String title) {
        StringBuilder builder = new StringBuilder(getSeparator());
        if (title.length() > builder.length()) {
            return "";
        }

        return builder.delete(builder.length() - title.length(), builder.length())
                .insert(builder.length() / 2, title).toString();

    }

    public static String getSeparator() {
        return "------------------------------------------";
    }

    public static void equalizeStringArray(String[] arr, String[] arr2) {
        int longestLength = -1;
        for (String s : arr) {
            if (s.length() > longestLength) longestLength = s.length();
        }
        for (String s : arr2) {
            if (s.length() > longestLength) longestLength = s.length();
        }
        for (int i = 0; i < arr.length; i++) {
            StringBuilder temp = new StringBuilder(arr[i]);
            while (temp.length() < longestLength) {
                temp.insert(0, "0");
            }
            arr[i] = temp.toString();
        }
        for (int i = 0; i < arr2.length; i++) {
            StringBuilder temp = new StringBuilder(arr2[i]);
            while (temp.length() < longestLength) {
                temp.insert(0, "0");
            }
            arr2[i] = temp.toString();
        }
    }

    public static String[] color(String... s) {
        for (int i = 0; i < s.length; i++)
            s[i] = color(s[i]);
        return s;
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
