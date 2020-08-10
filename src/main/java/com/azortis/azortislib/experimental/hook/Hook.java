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

package com.azortis.azortislib.experimental.hook;

import org.bukkit.plugin.java.JavaPlugin;

public interface Hook {
    /**
     * Called onEnable of an azortis plugin to hook into other plugins.
     *
     * @param plugin the plugin to use
     * @return whether successfully hooked or not
     */
    boolean hook(JavaPlugin plugin);

    /**
     * This returns the data of the hook, i.e. A manager or an instance of a plugin.
     *
     * @param <T> the object of data to get
     * @return The data
     */
    <T> T getHookData();

    /**
     * Returns the name of the hook.
     *
     * @return name of the hook
     */
    String getName();

}
