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

/**
 * A class which contains mathematical utility methods.
 */
@SuppressWarnings("unused")
public class MathUtil {
    /**
     * Takes the original value given and the values already given and finds the closest value to the original.
     * Originally used to determine which cardinal direction a player is facing based off of degrees.
     *
     * @param original The value to compare against the other values
     * @param values   The values which should be considered to find the closest.
     * @return Whichever value is closest to the original value.
     */
    public static int roundToClosest(int original, int... values) {
        int closest = Integer.MAX_VALUE;
        int value = 0;
        for (int i : values) {
            int range = Math.abs(i - original);
            if (range < closest) {
                value = i;
                closest = range;
            }
        }
        return value;
    }
}
