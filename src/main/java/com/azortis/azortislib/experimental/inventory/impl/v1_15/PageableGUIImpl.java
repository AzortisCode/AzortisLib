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

import com.azortis.azortislib.experimental.inventory.Item;
import com.azortis.azortislib.experimental.inventory.Page;
import com.azortis.azortislib.experimental.inventory.PageableGUI;
import com.azortis.azortislib.experimental.inventory.Template;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PageableGUIImpl extends GUIImpl implements PageableGUI {
    private final List<String> inventoryNames;
    private final List<Inventory> inventoryList;
    private final int pageSize;
    private Item[][] items;
    private int pages;

    // todo: make pageable template.
    public PageableGUIImpl(Template template, String name, boolean isGlobal, boolean isConfigurable, Consumer<Page> onUpdate, int pages) {
        super(template, name, isGlobal, isConfigurable, onUpdate);
        pageSize = template.getItems().length;
        this.pages = pages;
        inventoryNames = new ArrayList<>();
        inventoryList = new ArrayList<>();
        // todo remember to initialize items 2d array here w/ pageable template.
    }

    /**
     * Returns the number of pages
     *
     * @return the number of pages
     */
    @Override
    public int getPages() {
        return pages;
    }

    @Override
    public void addPages(int amount) {
        pages += amount;
        expandItemsArray();
    }

    protected void expandItemsArray() {
        Item[][] temp = new Item[pages][items[0].length]; // todo: finish this

    }

    /**
     * Returns the inventory of the gui.
     *
     * @return inventory of gui
     */
    @Override
    public @NotNull Inventory getInventory() {
        return getInventory(1);
    }

    /**
     * Sets the inventory name to a new name.
     *
     * @param inventoryName the inventory name
     */
    @Override
    public void setInventoryName(String inventoryName) {
        if (!inventoryNames.isEmpty())
            inventoryNames.set(0, inventoryName);
        else inventoryNames.add(inventoryName);
    }

    /**
     * Returns the display title of the inventory unformatted
     *
     * @return title of inventory
     */
    @Override
    public @NotNull String getTitle() {
        return getTitle(1);
    }

    /**
     * Returns the inventory's items in a mutable array form.
     *
     * @return inventory in array form.
     */
    @Override
    public @NotNull Item[] getItems() {
        // Have to map the 2d array to a 1d array.
        Item[] arr = new Item[items.length * items[0].length];
        int rowlength = items[0].length;
        for (int c = 0; c < items.length; c++) {
            System.arraycopy(items[c], 0, arr, c * rowlength, rowlength);
        }
        return arr;
    }

    /**
     * Gets an inventory from the specified page
     *
     * @param page the page of the inventory
     * @return the inventory
     */
    @Override
    public Inventory getInventory(int page) {
        return null; // todo: finish this
    }

    /**
     * Adds a singular page
     */
    @Override
    public void addPages() {
        addPages(1);
    }

    /**
     * Sets an item at the given page and slot if the page exists.
     *
     * @param page the page to use
     * @param slot the slot to use
     * @param item the item to set
     */
    @Override
    public boolean setItem(int page, int slot, Item item) {
        if (page <= pages && slot < pageSize && slot > -1 && page > 0) {
            items[page - 1][slot] = item;
            return true;
        }
        return false;
    }

    /**
     * Gets the items of the specified page
     *
     * @param page the page to target
     * @return the items on that page
     */
    @Override
    public Item[] getItems(int page) {
        return items[page - 1];
    }

    /**
     * Gets the title of the specified page
     *
     * @param page the page
     * @return the title of the given page
     */
    @Override
    public String getTitle(int page) {
        return inventoryNames.get(page);
    }

    /**
     * Sets the name of the specified page
     *
     * @param inventoryName name of the inventory
     * @param page          page to change name of
     */
    @Override
    public void setInventoryName(String inventoryName, int page) {
        if (inventoryNames.size() > page)
            inventoryNames.set(page, inventoryName);
        else if (inventoryNames.size() == page)
            inventoryNames.add(inventoryName);
    }
}
