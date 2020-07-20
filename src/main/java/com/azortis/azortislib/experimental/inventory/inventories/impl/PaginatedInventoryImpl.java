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

package com.azortis.azortislib.experimental.inventory.inventories.impl;

import com.azortis.azortislib.experimental.inventory.inventories.IInventory;
import com.azortis.azortislib.experimental.inventory.inventories.IPaginatedInventory;
import com.azortis.azortislib.experimental.inventory.item.Item;
import com.azortis.azortislib.experimental.inventory.template.Template;
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
    protected final List<Template> templates;
    protected final Map<Player, Integer> playerMap;
    protected Item[] items;
    protected String title;
    protected int pageSize;
    protected int pages;

    public PaginatedInventoryImpl(String title, List<Template> templates, Item[] items, int pageSize) {
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
        templates = new ArrayList<>();
        playerMap = new HashMap<>();
        pages = 1;
    }

    public PaginatedInventoryImpl(String title, int pageSize, int pages) {
        this.pageSize = pageSize;
        this.title = title;
        items = new Item[pageSize * pages];
        openConsumers = new ArrayList<>();
        closeConsumers = new ArrayList<>();
        templates = new ArrayList<>();
        this.playerMap = new HashMap<>();
        this.pages = pages;
    }

    /**
     * Used when a player opens up the inventory.
     *
     * @param player the player to add
     * @param page
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
        return templates.get(page);
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
        for (int i = 0; i < items.length; i++) {
            // STOPSHIP: 7/20/2020 this is where i stopped today at 1:31 AM, still implementing the paginated gui. Am going to implement serializable inventory support soon.
        }
    }

    /**
     * Returns the size of each page.
     *
     * @return the size of the page
     */
    @Override
    public int getPageSize() {
        return 0;
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

    }

    /**
     * Gets the page that a player is currently on.
     *
     * @param player the player to get
     * @return the page
     */
    @Override
    public int getPage(Player player) {
        return 0;
    }

    /**
     * Used when a player closes the inventory.
     *
     * @param player the player to remove
     */
    @Override
    public void removePlayer(Player player) {

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

    }

    /**
     * This updates all inventories of this type.
     * Does not change the title
     */
    @Override
    public void updateInventories() {

    }

    /**
     * This updates the inventory of the specified player if they have an inventory open of the same type
     *
     * @param player {@link Player} this player to update
     */
    @Override
    public void updateInventory(@NotNull Player player) {

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
        return null;
    }

    /**
     * Returns the size of the all items.
     *
     * @return the size of the inventory
     */
    @Override
    public int getSize() {
        return 0;
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
        return null;
    }

    /**
     * The actions that are performed when the menu is constructed.
     *
     * @param consumer {@link Consumer < IInventory >} The action that is called when a menu is constructed
     */
    @Override
    public void construct(@NotNull Consumer<IInventory> consumer) {

    }

    /**
     * Returns the name of the inventory.
     *
     * @return title of inventory
     */
    @Override
    public @NotNull String getTitle() {
        return null;
    }

    /**
     * Sets the title of the inventory.
     *
     * @param title The title of the inventory - color code supported.
     */
    @Override
    public void setTitle(@NotNull String title) {

    }

    /**
     * Add a condition which is checked when the inventory is opened.
     *
     * @param consumer {@link Consumer< InventoryOpenEvent >} the condition
     */
    @Override
    public void addOpenConsumer(@NotNull Consumer<InventoryOpenEvent> consumer) {

    }

    /**
     * Add a condition which is checked when the inventory is closed.
     *
     * @param consumer {@link Consumer< InventoryCloseEvent >} the condition
     */
    @Override
    public void addCloseConsumer(@NotNull Consumer<InventoryCloseEvent> consumer) {

    }

    /**
     * Get all conditions which are checked when the inventory is opened.
     *
     * @return {@link List <Consumer>} a set of conditions which do commands
     */
    @Override
    public @NotNull List<Consumer<InventoryOpenEvent>> getOpenConsumers() {
        return null;
    }

    /**
     * Get all conditions which are checked when the inventory is closed.
     *
     * @return {@link List<Consumer>} a set of conditions which do commands
     */
    @Override
    public @NotNull List<Consumer<InventoryCloseEvent>> getCloseConsumers() {
        return null;
    }

    /**
     * Sets items in the inventory corresponding to it's slot in the array and the item itself.
     *
     * @param items an array of items to set
     */
    @Override
    public void setItems(Item[] items) {

    }

    /**
     * This closes all inventories that are associated with this api.
     */
    @Override
    public void closeAllInventories() {

    }
}
