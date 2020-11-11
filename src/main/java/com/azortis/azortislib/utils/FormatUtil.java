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

import java.util.List;

/**
 * A utility class which contains utility methods for formatting strings.
 */
@SuppressWarnings("unused")
public class FormatUtil {
    /**
     * Gets a seperator that has a title added to the middle of it.
     *
     * @param title the title to add
     * @return A string separator with a title added to it
     */
    public static String getSeparator(String title) {
        StringBuilder builder = new StringBuilder(getSeparator());
        if (title.length() > builder.length()) {
            return "";
        }

        return builder.delete(builder.length() - title.length(), builder.length())
                .insert(builder.length() / 2, title).toString();

    }

    /**
     * Get a separator to be used in console
     *
     * @return A string separator to be used in console.
     */
    public static String getSeparator() {
        return "------------------------------------------";
    }

    /**
     * Used to take two string arrays, and equalize the length of each string in both string arrays to the
     * largest string length by adding 0's in front.
     * <p>
     * Originally used to format versions and then used to compare version numbers.
     *
     * @param arr  One of the arrays to equalize
     * @param arr2 The second array to equalize.
     */
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

    /**
     * Translates color codes in messages to their bukkit equivalents ready to be sent to players.
     *
     * @param s the string to translate
     * @return an array of translated strings.
     */
    public static String[] color(String... s) {
        for (int i = 0; i < s.length; i++)
            s[i] = color(s[i]);
        return s;
    }

    /**
     * Translates color codes in messages to their bukkit equivalents ready to be sent to players.
     *
     * @param s the string to translate
     * @return an the translated string.
     */
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> color(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;
    }
}
