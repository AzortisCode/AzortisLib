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

package com.azortis.azortislib.experimental.inventory;

import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public interface GUI extends Listener {
    /**
     * Returns the inventory of the gui.
     *
     * @return inventory of gui
     */
    @NotNull Inventory getInventory();

    /**
     * Returns the inventory in an array form.
     *
     * @return inventory in array form.
     */
    @NotNull Item[] getItems();

    /**
     * A global page is a page which has multiple viewers.
     *
     * @return whether the page is global
     */
    boolean isGlobal();

    /**
     * Returns the unique name of the gui.
     *
     * @return uuid
     */
    @NotNull String getGUIName();

    /**
     * Returns the display title of the inventory
     *
     * @return title of inventory
     */
    @NotNull String getTitle();

    /**
     * Returns the configuration of the inventory as a {@link Template}
     *
     * @return the configuration of the inventory
     */
    @Nullable Template getConfiguration();

    /**
     * Returns whether the gui is configurable by server owners.
     *
     * @return whether the gui is configurable
     */
    boolean isConfigurable();

    /**
     * Sets the inventory name to a new name.
     *
     * @param inventoryName the inventory name
     */
    void setInventoryName(String inventoryName);

    /**
     * This action is called when the page is updated
     *
     * @return action called on page update
     */
    Consumer<Page> getUpdate();

}
