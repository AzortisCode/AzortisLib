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

package com.azortis.azortislib.inventory.inventories.impl;

import com.azortis.azortislib.inventory.inventories.IInventory;
import com.azortis.azortislib.inventory.item.Item;
import com.azortis.azortislib.inventory.template.Template;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InventoryImpl implements IInventory {
    protected final Item[] items;
    protected final List<Consumer<InventoryOpenEvent>> openConsumers;
    protected final List<Consumer<InventoryCloseEvent>> closeConsumers;
    protected String title;
    protected Template template;

    public InventoryImpl(Item[] items, String title) {
        this.items = items;
        this.title = title;
        openConsumers = new ArrayList<>();
        closeConsumers = new ArrayList<>();
    }

    /**
     * Gets a per-player inventory. This returns a new inventory every time this command is used, but with the same
     * or similar contents.
     *
     * @return {@link Inventory} a personalized inventory for per-player inventories.
     */
    @Override
    public @NotNull Inventory getInventory() {
        Inventory i = Bukkit.createInventory(this, items.length, title);
        i.setStorageContents(items);
        return i;
    }

    /**
     * The actions that are performed when the menu is constructed.
     *
     * @param consumer {@link Consumer <IInventory>} The action that is called when a menu is constructed
     */
    @Override
    public void construct(@NotNull Consumer<IInventory> consumer) {
        consumer.accept(this);
    }

    /**
     * Returns the name of the inventory.
     *
     * @return title of inventory
     */
    @Override
    public @NotNull String getTitle() {
        return title;
    }

    /**
     * Sets the title of the inventory.
     *
     * @param title The title of the inventory - color code supported.
     */
    @Override
    public void setTitle(@NotNull String title) {
        this.title = ChatColor.translateAlternateColorCodes('&', title);
    }

    /**
     * Returns the size of the inventory.
     *
     * @return the size of the inventory
     */
    @Override
    public int getSize() {
        return items.length;
    }

    /**
     * Add a condition which is checked when the inventory is opened.
     *
     * @param consumer {@link Consumer< InventoryOpenEvent >} the condition
     */
    @Override
    public void addOpenConsumer(@NotNull Consumer<InventoryOpenEvent> consumer) {
        openConsumers.add(consumer);
    }

    /**
     * Add a condition which is checked when the inventory is closed.
     *
     * @param consumer {@link Consumer< InventoryCloseEvent >} the condition
     */
    @Override
    public void addCloseConsumer(@NotNull Consumer<InventoryCloseEvent> consumer) {
        closeConsumers.add(consumer);
    }

    /**
     * Get all conditions which are checked when the inventory is opened.
     *
     * @return {@link List <Consumer>} a set of conditions which do commands
     */
    @Override
    public @NotNull List<Consumer<InventoryOpenEvent>> getOpenConsumers() {
        return openConsumers;
    }

    /**
     * Get all conditions which are checked when the inventory is closed.
     *
     * @return {@link List<Consumer>} a set of conditions which do commands
     */
    @Override
    public @NotNull List<Consumer<InventoryCloseEvent>> getCloseConsumers() {
        return closeConsumers;
    }

    /**
     * Get the Item at the specified slot.
     *
     * @param slot the slot at which the item resides at
     * @return {@link Item}item at the specified slot in the inventory
     */
    @Override
    public @Nullable Item getItem(int slot) {
        if (slot >= items.length) return null;
        return items[slot];
    }

    /**
     * Set an item at the slot specified. Setting item to null results in removing the item in that slot globally.
     *
     * @param slot slot that the item will reside in
     * @param item {@link Item} the item to set the slot to
     */
    @Override
    public void setItem(int slot, @Nullable Item item) {
        if (slot >= items.length) return;
        items[slot] = item;
    }

    /**
     * Sets items in the inventory corresponding to it's slot in the array and the item itself.
     *
     * @param items an array of items to set
     */
    @Override
    public void setItems(Item[] items) {
        for (int i = 0; i < items.length && i < this.items.length; i++) {
            if (items[i] != null)
                this.items[i] = items[i];
        }
    }

    /**
     * ==Internal use only==
     * Stores the template in some form to get the template later.
     *
     * @param template {@link Template} the template to store
     */
    @Override
    public void addTemplate(@NotNull Template template) {
        this.template = template;
    }

    /**
     * Returns the template currently being used.
     *
     * @return {@link Template} the template currently being used.
     */
    @Override
    public Template getTemplate() {
        return template;
    }

    /**
     * This resets all contents within the inventory and updates it. The inventory must be of the same size as this one.
     * Does not change the title
     *
     * @param inventory {@link Inventory} the inventory to update.
     */
    @Override
    public void updateInventory(@NotNull Inventory inventory) {
        if (inventory.getHolder() instanceof InventoryImpl) {
            inventory.setStorageContents(items);
        }
    }

    /**
     * This updates all inventories.
     */
    @Override
    public void updateInventories() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            InventoryView i = onlinePlayer.getOpenInventory();
            if (i.getTopInventory().getHolder() instanceof InventoryImpl) {
                i.getTopInventory().setStorageContents(items);
            }
        }
    }

    /**
     * This updates the inventory of the specified player if they have an inventory open of this type.
     *
     * @param player {@link Player} this player to update
     */
    @Override
    public void updateInventory(@NotNull Player player) {
        if (player.getOpenInventory().getTopInventory().getHolder() instanceof InventoryImpl) {
            player.getOpenInventory().getTopInventory().setStorageContents(items);
        }
    }

    /**
     * This closes all inventories that are associated with this api.
     */
    @Override
    public void closeAllInventories() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            InventoryView i = onlinePlayer.getOpenInventory();
            if (i.getTopInventory().getHolder() instanceof IInventory) {
                onlinePlayer.closeInventory();
            }
        }
    }
}
