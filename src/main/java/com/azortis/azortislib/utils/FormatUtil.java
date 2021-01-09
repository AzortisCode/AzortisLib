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
