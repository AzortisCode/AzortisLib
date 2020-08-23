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

package com.azortis.azortislib.experimental.inventory.impl.v1_15;

import com.azortis.azortislib.experimental.inventory.GUI;
import com.azortis.azortislib.experimental.inventory.Page;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class PageImpl implements Page {
    protected final Inventory inventory;
    protected final String title;
    protected final GUI gui;
    protected final UUID uuid;

    public PageImpl(GUI gui, UUID uuid) {
        this.inventory = gui.getInventory();
        this.title = gui.getTitle();
        this.uuid = uuid;
        this.gui = gui;
    }

    /**
     * Get the object's inventory.
     *
     * @return stored inventory
     */
    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    /**
     * A global page is a page which has multiple viewers.
     *
     * @return whether the page is global
     */
    @Override
    public boolean isGlobal() {
        return gui.isGlobal();
    }

    /**
     * Returns the UUID of the inventory.
     *
     * @return uuid
     */
    @Override
    public @NotNull UUID getUUID() {
        return uuid;
    }

    /**
     * Returns the display title of the inventory
     *
     * @return title of inventory
     */
    @Override
    public @NotNull String getTitle() {
        return title;
    }

    /**
     * Gets the GUI connected to this page.
     *
     * @return the gui
     */
    @Override
    public @NotNull GUI getGUI() {
        return gui;
    }
}
