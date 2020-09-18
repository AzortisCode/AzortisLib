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

import com.azortis.azortislib.experimental.inventory.*;
import com.azortis.azortislib.utils.FormatUtil;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class GUIImpl implements GUI {
    protected final Template template;
    protected final String name;
    protected final boolean global;
    protected final boolean configurable;
    protected final Consumer<Page> onUpdate;
    protected Inventory inventory;
    protected final Item[] items;
    protected String inventoryName;

    public GUIImpl(Template template, String name, boolean isGlobal, boolean isConfigurable, Consumer<Page> onUpdate) {
        this.template = template;
        this.name = name;
        items = template.getItems();
        global = isGlobal;
        configurable = isConfigurable;
        this.onUpdate = onUpdate;

    }

    /**
     * This action is called when the page is updated
     *
     * @return action called on page update
     */
    @Override
    public Consumer<Page> getUpdate() {
        return onUpdate;
    }

    /**
     * Returns the inventory of the gui.
     *
     * @return inventory of gui
     */
    @Override
    public @NotNull Inventory getInventory() {
        if (global) {
            if (inventory == null)
                inventory = generateInventory();
            return inventory;
        }

        return generateInventory();
    }

    private Inventory generateInventory() {
        Page p = GUIManager.getInstance().getEngine().createPage(this);
        Inventory i = Bukkit.createInventory(p, items.length, FormatUtil.color(inventoryName));
        p.setInventory(i);
        ItemStack[] itemStacks = new ItemStack[items.length];
        for (int i1 = 0; i1 < items.length; i1++) {
            if (items[i1] == null || items[i1].getItemStack() == null) continue;
            itemStacks[i1] = items[i1].getItemStack();
        }
        i.setContents(itemStacks);
        return i;
    }

    /**
     * Returns the inventory's items in a mutable array form.
     *
     * @return inventory in array form.
     */
    @Override
    public @NotNull Item[] getItems() {
        return items;
    }

    /**
     * A global page is a page which has multiple viewers.
     *
     * @return whether the page is global
     */
    @Override
    public boolean isGlobal() {
        return global;
    }

    /**
     * Returns the unique name of the gui.
     *
     * @return uuid
     */
    @Override
    public @NotNull String getGUIName() {
        return name;
    }

    /**
     * Returns the display title of the inventory unformatted
     *
     * @return title of inventory
     */
    @Override
    public @NotNull String getTitle() {
        return inventoryName;
    }

    /**
     * Returns the configuration of the inventory as a {@link Template}
     *
     * @return the configuration of the inventory
     */
    @Override
    public @Nullable Template getConfiguration() {
        return template;
    }

    /**
     * Returns whether the gui is configurable by server owners.
     *
     * @return whether the gui is configurable
     */
    @Override
    public boolean isConfigurable() {
        return configurable;
    }

    /**
     * Sets the inventory name to a new name.
     *
     * @param inventoryName the inventory name
     */
    @Override
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }
}
