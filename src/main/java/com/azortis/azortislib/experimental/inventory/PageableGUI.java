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

public interface PageableGUI extends GUI {
    /**
     * Gets an inventory from the specified page
     *
     * @param page the page of the inventory
     * @return the inventory
     */
    Inventory getInventory(int page);

    /**
     * Gets all items in all pages in array form.
     *
     * @param page the page to target
     * @return the items on that page
     */
    Item[] getItems(int page);

    /**
     * Gets the title of the specified page
     *
     * @param page the page
     * @return the title of the given page
     */
    String getTitle(int page);

    /**
     * Sets the name of the specified page
     *
     * @param inventoryName name of the inventory
     * @param page          page to change name of
     */
    void setInventoryName(String inventoryName, int page);

    /**
     * Adds a singular page
     */
    void addPages();

    /**
     * Adds multiple pages to the gui at the same time.
     *
     * @param amount number of pages
     */
    void addPages(int amount);

    /**
     * Returns the number of pages
     *
     * @return the number of pages
     */
    int getPages();

    /**
     * Sets an item at the given page and slot if the page exists.
     *
     * @param page the page to use
     * @param slot the slot to use
     * @param item the item to set
     * @return whether the item was set or not
     */
    boolean setItem(int page, int slot, Item item);

}
