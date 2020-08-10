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
import com.azortis.azortislib.inventory.inventories.IPaginatedInventory;
import com.azortis.azortislib.inventory.item.Item;
import com.azortis.azortislib.inventory.template.Template;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public class PaginatedInventoryImpl implements IPaginatedInventory {
    protected final List<Consumer<InventoryOpenEvent>> openConsumers;
    protected final List<Consumer<InventoryCloseEvent>> closeConsumers;
    protected final Map<Player, Integer> playerMap;
    protected Template[] templates;
    protected Item[] items;
    protected String title;
    protected int pageSize;
    protected int pages;

    public PaginatedInventoryImpl(String title, Template[] templates, Item[] items, int pageSize) {
        this.pageSize = pageSize;
        this.title = title;
        this.templates = templates;
        this.items = items;
        openConsumers = new ArrayList<>();
        closeConsumers = new ArrayList<>();
        playerMap = new HashMap<>();
        pages = 1;
    }

    public PaginatedInventoryImpl(Item[] items, String title, int pageSize) {
        this.pageSize = pageSize;
        this.items = items;
        this.title = title;
        openConsumers = new ArrayList<>();
        closeConsumers = new ArrayList<>();
        templates = new Template[pages];
        playerMap = new HashMap<>();
        pages = 1;
    }

    public PaginatedInventoryImpl(String title, int pageSize, int pages) {
        this.pageSize = pageSize;
        this.title = title;
        items = new Item[pageSize * pages];
        openConsumers = new ArrayList<>();
        closeConsumers = new ArrayList<>();
        templates = new Template[pages];
        this.playerMap = new HashMap<>();
        this.pages = pages;
    }

    /**
     * Used when a player opens up the inventory.
     *
     * @param player the player to add
     * @param page   the page the to add the player to
     */
    @Override
    public void addPlayer(Player player, int page) {
        playerMap.put(player, page);
    }

    /**
     * Returns the template being used on the specified page.
     *
     * @param page the page
     * @return {@link Template} the template currently being used.
     */
    @Override
    public Template getTemplate(int page) {
        return templates[page];
    }

    /**
     * Sets items in the specific page.
     *
     * @param items the items to set
     * @param page  the size of the page
     */
    @Override
    public void setItems(Item[] items, int page) {
        int startingPoint = (page * pageSize) - pageSize;
        if (startingPoint + pageSize > pages * pageSize) {
            Item[] tmp = this.items;
            this.items = Arrays.copyOf(tmp, page * pageSize);
        }
        System.arraycopy(items, 0, this.items, startingPoint, items.length);
    }

    /**
     * Returns the size of each page.
     *
     * @return the size of the page
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * ==Internal use only==
     * <p>
     * Adds a template to the specified page.
     *
     * @param template {@link Template} the template to add
     * @param page     the page to add the template to
     */
    @Override
    public void addTemplate(Template template, int page) {
        if (templates.length <= page) {
            // expand array size
            Template[] tmp = templates;
            templates = Arrays.copyOf(tmp, tmp.length + 20);
        }
        templates[page] = template;
    }

    /**
     * Gets the page that a player is currently on.
     *
     * @param player the player to get
     * @return the page
     */
    @Override
    public int getPage(Player player) {
        return playerMap.get(player);
    }

    /**
     * Used when a player closes the inventory.
     *
     * @param player the player to remove
     */
    @Override
    public void removePlayer(Player player) {
        playerMap.remove(player);
    }

    /**
     * This resets all contents within the inventory and updates it. The inventory must be of the same type
     * Does not change the title
     *
     * @param inventory {@link Inventory} the inventory to update.
     * @param page      the page to update it to
     */
    @Override
    public void updateInventory(@NotNull Inventory inventory, int page) {
        if (inventory.getHolder() instanceof PaginatedInventoryImpl) {
            PaginatedInventoryImpl holder = (PaginatedInventoryImpl) inventory.getHolder();
            inventory.setStorageContents(Arrays.copyOfRange(holder.items,
                    holder.getPageSize() * page,
                    (holder.getPageSize() * page) + holder.getPageSize()));
        }
    }

    /**
     * This updates all inventories of this type.
     * Does not change the title
     */
    @Override
    public void updateInventories() {
        Bukkit.getOnlinePlayers().forEach(this::updateInventory);
    }

    /**
     * This updates the inventory of the specified player if they have an inventory open of the same type
     *
     * @param player {@link Player} this player to update
     */
    @Override
    public void updateInventory(@NotNull Player player) {
        Inventory inventory = player.getOpenInventory().getTopInventory();
        if (inventory.getHolder() instanceof PaginatedInventoryImpl) {
            PaginatedInventoryImpl holder = (PaginatedInventoryImpl) inventory.getHolder();
            inventory.setStorageContents(Arrays.copyOfRange(holder.items,
                    holder.getPageSize() * holder.getPage(player),
                    (holder.getPageSize() * holder.getPage(player)) + holder.getPageSize()));
        }
    }

    /**
     * Set an item at the slot specified on the specified page.
     * Setting item to null results in removing the item in that slot.
     *
     * @param slot slot that the item will reside in
     * @param page the page to set
     * @param item {@link Item} the item to set the slot to
     */
    @Override
    public void setItem(int slot, int page, @Nullable Item item) {
        items[(page * pageSize) + slot] = item;
    }

    /**
     * Get the Item at the specified slot on the specified page.
     *
     * @param slot the slot at which the item resides at
     * @param page the page to use
     * @return {@link Item}item at the specified slot in the inventory
     */
    @Override
    public @Nullable Item getItem(int slot, int page) {
        return items[(page * pageSize) + slot];
    }

    /**
     * Returns the size of the all items.
     *
     * @return the size of the inventory
     */
    @Override
    public int getSize() {
        return items.length;
    }

    /**
     * Gets a per-player inventory. This returns a new inventory every time this command is used, but with the same
     * or similar contents of the specified page.
     *
     * @param page the page
     * @return {@link Inventory} a personalized inventory for per-player inventories.
     */
    @Override
    public @NotNull Inventory getInventory(int page) {
        Inventory inventory = Bukkit.createInventory(this, pageSize, ChatColor.translateAlternateColorCodes('&', title));
        inventory.setStorageContents(Arrays.copyOfRange(items, (page * pageSize), (page * pageSize) + pageSize));
        return inventory;
    }

    /**
     * The actions that are performed when the menu is constructed.
     *
     * @param consumer {@link Consumer < IInventory >} The action that is called when a menu is constructed
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
        this.title = title;
    }

    /**
     * Add a condition which is checked when the inventory is opened.
     *
     * @param consumer {@link Consumer<InventoryOpenEvent>} the condition
     */
    @Override
    public void addOpenConsumer(@NotNull Consumer<InventoryOpenEvent> consumer) {
        openConsumers.add(consumer);
    }

    /**
     * Add a condition which is checked when the inventory is closed.
     *
     * @param consumer {@link Consumer<InventoryCloseEvent>} the condition
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
     * Sets items in the inventory corresponding to it's slot in the array and the item itself.
     *
     * @param items an array of items to set
     */
    @Override
    public void setItems(Item[] items) {
        System.arraycopy(items, 0, this.items, 0, items.length);
    }

    /**
     * This closes all inventories that are associated with this api.
     */
    @Override
    public void closeAllInventories() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.getOpenInventory().getTopInventory().getHolder() instanceof PaginatedInventoryImpl)
                player.closeInventory();
        });
    }
}
