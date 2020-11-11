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

import com.azortis.azortislib.experimental.inventory.item.Button;
import com.azortis.azortislib.experimental.inventory.item.ButtonSlot;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * An interface which represents a singular page in a GUI.
 */
@SuppressWarnings("unused")
public interface Page {
    /**
     * Gets the page's inventory name.
     *
     * @return The name of the inventory to be used when building new inventories.
     */
    String getInventoryName();

    /**
     * Sets the inventory name to the specified name.
     *
     * @param name the name of the inventory
     */
    void setInventoryName(String name);

    /**
     * Does the page use a shared view or per-player view.
     *
     * @return whether each view is global or single.
     */
    boolean isGlobal();

    /**
     * Sets whether the page uses a shared view or per-player view.
     *
     * @param global whether the page is global or not
     */
    void setGlobal(boolean global);

    /**
     * Gets all views currently open.
     *
     * @return returns an immutable set of all views.
     */
    @NotNull
    Set<View> getViews();

    /**
     * Returns the size of the inventory.
     *
     * @return the size of the inventory
     */
    int inventorySize();

    /**
     * Get the close action that is called if the inventory is closed.
     *
     * @return the BiConsumer called when the inventory is closed.
     */
    @NotNull
    BiConsumer<InventoryCloseEvent, View> closeAction();

    /**
     * Sets the close action that the inventory uses.
     *
     * @param closeAction the action used when the inventory is closed.
     */
    void setCloseAction(@NotNull BiConsumer<InventoryCloseEvent, View> closeAction);

    /**
     * Removes all references to the view from this page.
     *
     * @param view the view to dispose
     */
    void disposeView(@NotNull View view);

    /**
     * Gets the parent GUI of this page.
     *
     * @return the GUI parent of this page.
     */
    @NotNull
    GUI getGUI();

    /**
     * Returns a list of button slots and items.
     *
     * @return a list of items
     */
    @NotNull
    List<ButtonSlot> items();

    /**
     * Adds an item in the specified slot. Overrides pre-existing items.
     *
     * @param slot   the slot to add the item in
     * @param button the item to add.
     * @throws IllegalArgumentException if slot < 1
     */
    void addButton(int slot, @NotNull Button button);

    /**
     * Removes the specified item from the list of items.
     *
     * @param slot the slot of the item
     * @throws IllegalArgumentException if slot < 1
     */
    void removeButton(int slot);

    /**
     * Gets a button from the given slot.
     *
     * @param slot the slot of the button
     * @return {@link Button} the button.
     * @throws IllegalArgumentException if slot < 1
     */
    Button getButton(int slot);
}
