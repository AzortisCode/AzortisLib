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

package com.azortis.azortislib.experimental.inventory.inventories;

import com.azortis.azortislib.experimental.inventory.exceptions.TemplateNotSupportedException;
import com.azortis.azortislib.experimental.inventory.item.Item;
import com.azortis.azortislib.experimental.inventory.template.Template;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IPaginatedInventory extends IInventory {
    /**
     * Returns the template being used on the first page.
     *
     * @return {@link Template} the template currently being used.
     */
    @Override
    default Template getTemplate() {
        return getTemplate(1);
    }

    /**
     * Set the current inventory template on the first page to the specified one.
     * Throws {@link TemplateNotSupportedException} when the template size is not supported.
     *
     * @param template {@link Template} the template to set the inventory to.
     * @throws TemplateNotSupportedException is thrown when the template size is not supported
     */
    @Override
    default void setTemplate(@NotNull Template template) {
        setTemplate(template, 1);
    }

    /**
     * Returns the template being used on the specified page.
     *
     * @param page the page
     * @return {@link Template} the template currently being used.
     */
    Template getTemplate(int page);

    /**
     * Set the current inventory template on the page to the specified one.
     * Throws {@link TemplateNotSupportedException} when the template size is not supported.
     *
     * @param page     the page to set the template to
     * @param template {@link Template} the template to set the inventory to.
     * @throws TemplateNotSupportedException is thrown when the template size is not supported
     */
    default void setTemplate(@NotNull Template template, int page) {
        if (template.isAdjustable()) {
            setItems(template.getItems(getPageSize()), page);
        } else if (template.isMultiTemplate()) {
            for (int i : template.getMultiSizing()) {
                if (i == getPageSize()) {
                    setItems(template.getItems(i));
                    break;
                }
            }
        } else if (template.getSize() == getPageSize()) {
            setItems(template.getItems(), page);
        } else {
            throw new TemplateNotSupportedException("Template size does not match inventory size.");
        }
        addTemplate(template, page);
    }

    /**
     * Sets items in the specific page.
     *
     * @param items the items to set
     * @param page  the size of the page
     */
    void setItems(Item[] items, int page);

    /**
     * Returns the size of each page.
     *
     * @return the size of the page
     */
    int getPageSize();

    /**
     * ==Internal use only==
     * <p>
     * Adds a template to the specified page.
     *
     * @param template {@link Template} the template to add
     * @param page     the page to add the template to
     */
    void addTemplate(Template template, int page);

    /**
     * This resets all contents within the inventory and updates it. The inventory must be of the same type
     * Does not change the title. This updates it to the first page of the gui.
     *
     * @param inventory {@link Inventory} the inventory to update.
     */
    @Override
    default void updateInventory(@NotNull Inventory inventory) {
        updateInventory(inventory, 1);
    }

    /**
     * Gets the page that a player is currently on.
     *
     * @param player the player to get
     * @return the page
     */
    int getPage(Player player);

    /**
     * Used when a player opens up the inventory.
     *
     * @param player the player to add
     */
    void addPlayer(Player player, int page);

    /**
     * Used when a player closes the inventory.
     *
     * @param player the player to remove
     */
    void removePlayer(Player player);

    /**
     * This resets all contents within the inventory and updates it. The inventory must be of the same type
     * Does not change the title
     *
     * @param inventory {@link Inventory} the inventory to update.
     * @param page      the page to update it to
     */
    void updateInventory(@NotNull Inventory inventory, int page);

    /**
     * This updates all inventories of this type.
     * Does not change the title
     */
    @Override
    void updateInventories();

    /**
     * This updates the inventory of the specified player if they have an inventory open of the same type
     *
     * @param player {@link Player} this player to update
     */
    @Override
    void updateInventory(@NotNull Player player);

    /**
     * ==Internal use only==
     * Stores the template in some form to get the template later.
     *
     * @param template {@link Template} the template to store
     */
    @Override
    default void addTemplate(@NotNull Template template) {
        addTemplate(template, 1);
    }

    /**
     * Set an item at the slot specified on the first page.
     * Setting item to null results in removing the item in that slot.
     *
     * @param slot slot that the item will reside in
     * @param item {@link Item} the item to set the slot to
     */
    @Override
    default void setItem(int slot, @Nullable Item item) {
        setItem(slot, 1, item);
    }

    /**
     * Set an item at the slot specified on the specified page.
     * Setting item to null results in removing the item in that slot.
     *
     * @param slot slot that the item will reside in
     * @param item {@link Item} the item to set the slot to
     * @param page the page to set
     */
    void setItem(int slot, int page, @Nullable Item item);

    /**
     * Get the Item at the specified slot on the first page.
     *
     * @param slot the slot at which the item resides at
     * @return {@link Item}item at the specified slot in the inventory
     */
    @Override
    @Nullable
    default Item getItem(int slot) {
        return getItem(slot, 1);
    }

    /**
     * Get the Item at the specified slot on the specified page.
     *
     * @param slot the slot at which the item resides at
     * @param page the page to use
     * @return {@link Item}item at the specified slot in the inventory
     */
    @Nullable Item getItem(int slot, int page);

    /**
     * Returns the size of the all items.
     *
     * @return the size of the inventory
     */
    @Override
    int getSize();

    /**
     * Gets a per-player inventory. This returns a new inventory every time this command is used, but with the same
     * or similar contents. This returns the first page.
     *
     * @return {@link Inventory} a personalized inventory for per-player inventories.
     */
    @Override
    @NotNull
    default Inventory getInventory() {
        return getInventory(1);
    }

    /**
     * Gets a per-player inventory. This returns a new inventory every time this command is used, but with the same
     * or similar contents of the specified page.
     *
     * @param page the page
     * @return {@link Inventory} a personalized inventory for per-player inventories.
     */
    @NotNull Inventory getInventory(int page);
}
