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

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface Page extends InventoryHolder {
    /**
     * Get the object's inventory.
     *
     * @return stored inventory
     */
    @Override
    @NotNull Inventory getInventory();

    /**
     * A global page is a page which has multiple viewers.
     *
     * @return whether the page is global
     */
    boolean isGlobal();

    /**
     * Returns the UUID of the inventory.
     *
     * @return uuid
     */
    @NotNull UUID getUUID();

    /**
     * Returns the display title of the inventory
     *
     * @return title of inventory
     */
    @NotNull String getTitle();

    /**
     * Gets the GUI connected to this page.
     *
     * @return the gui
     */
    @NotNull GUI getGUI();

    /**
     * A method called every 5 ticks and is used to update the page.
     */
    default void update() {
        getGUI().getUpdate().accept(this);
    }

    /**
     * Sets the inventory that the page holds.
     *
     * @param inventory inventory the page holds
     */
    void setInventory(Inventory inventory);
}
