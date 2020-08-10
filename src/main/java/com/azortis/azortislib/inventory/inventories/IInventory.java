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

package com.azortis.azortislib.inventory.inventories;

import com.azortis.azortislib.inventory.exceptions.TemplateNotSupportedException;
import com.azortis.azortislib.inventory.item.Item;
import com.azortis.azortislib.inventory.template.Template;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public interface IInventory extends InventoryHolder {

    /**
     * Gets a per-player inventory. This returns a new inventory every time this command is used, but with the same
     * or similar contents.
     *
     * @return {@link Inventory} a personalized inventory for per-player inventories.
     */
    @Override
    @NotNull Inventory getInventory();

//   todo: make a customized global inventory menu @NotNull Inventory getGlobalInventory();, also make a persistent inventory

    /**
     * The actions that are performed when the menu is constructed.
     *
     * @param consumer {@link Consumer<IInventory>} The action that is called when a menu is constructed
     */
    void construct(@NotNull Consumer<IInventory> consumer);

    /**
     * Returns the name of the inventory.
     *
     * @return title of inventory
     */
    @NotNull
    String getTitle();

    /**
     * Sets the title of the inventory.
     *
     * @param title The title of the inventory - color code supported.
     */
    void setTitle(@NotNull String title);

    /**
     * Returns the size of the inventory.
     *
     * @return the size of the inventory
     */
    int getSize();

    /**
     * Add a condition which is checked when the inventory is opened.
     *
     * @param consumer {@link Consumer<InventoryOpenEvent>} the condition
     */
    void addOpenConsumer(@NotNull Consumer<InventoryOpenEvent> consumer);

    /**
     * Add a condition which is checked when the inventory is closed.
     *
     * @param consumer {@link Consumer<InventoryCloseEvent>} the condition
     */
    void addCloseConsumer(@NotNull Consumer<InventoryCloseEvent> consumer);

    /**
     * Get all conditions which are checked when the inventory is opened.
     *
     * @return {@link List<Consumer>} a set of conditions which do commands
     */
    @NotNull
    List<Consumer<InventoryOpenEvent>> getOpenConsumers();

    /**
     * Get all conditions which are checked when the inventory is closed.
     *
     * @return {@link List<Consumer>} a set of conditions which do commands
     */
    @NotNull
    List<Consumer<InventoryCloseEvent>> getCloseConsumers();

    /**
     * Get the Item at the specified slot.
     *
     * @param slot the slot at which the item resides at
     * @return {@link Item}item at the specified slot in the inventory
     */
    @Nullable
    Item getItem(int slot);

    /**
     * Set an item at the slot specified. Setting item to null results in removing the item in that slot globally.
     *
     * @param slot slot that the item will reside in
     * @param item {@link Item} the item to set the slot to
     */
    void setItem(int slot, @Nullable Item item);

    /**
     * Sets items in the inventory corresponding to it's slot in the array and the item itself.
     *
     * @param items an array of items to set
     */
    void setItems(Item[] items);

    /**
     * ==Internal use only==
     * Stores the template in some form to get the template later.
     *
     * @param template {@link Template} the template to store
     */
    void addTemplate(@NotNull Template template);

    /**
     * Returns the template currently being used.
     *
     * @return {@link Template} the template currently being used.
     */
    Template getTemplate();

    /**
     * Set the current inventory template to the specified one.
     * Throws {@link TemplateNotSupportedException} when the template size is not supported.
     *
     * @param template {@link Template} the template to set the inventory to.
     * @throws TemplateNotSupportedException is thrown when the template size is not supported
     */
    default void setTemplate(@NotNull Template template) {
        if (template.isAdjustable()) {
            setItems(template.getItems(getSize()));
        } else if (template.isMultiTemplate()) {
            for (int i : template.getMultiSizing()) {
                if (i == getSize()) {
                    setItems(template.getItems(i));
                    break;
                }
            }
        } else if (template.getSize() == getSize()) {
            setItems(template.getItems());
        } else {
            throw new TemplateNotSupportedException("Template size does not match inventory size.");
        }
        addTemplate(template);
    }

    /**
     * This resets all contents within the inventory and updates it. The inventory must be of the same type
     * Does not change the title
     *
     * @param inventory {@link Inventory} the inventory to update.
     */
    void updateInventory(@NotNull Inventory inventory);

    /**
     * This updates all inventories from this api.
     * Does not change the title
     */
    void updateInventories();


    /**
     * This updates the inventory of the specified player if they have an inventory open of the same type
     *
     * @param player {@link Player} this player to update
     */
    void updateInventory(@NotNull Player player);

    /**
     * This closes all inventories that are associated with this api.
     */
    void closeAllInventories();
}
