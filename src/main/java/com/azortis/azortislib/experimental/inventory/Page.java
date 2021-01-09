/*
 * MIT License
 *
 * Copyright (c) 2021 Azortis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.azortis.azortislib.experimental.inventory;

import com.azortis.azortislib.experimental.inventory.item.Button;
import com.azortis.azortislib.experimental.inventory.item.ButtonSlot;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
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

    /**
     * Used when getting an instance of the inventory from the Page.
     *
     * @return {@link Inventory} an instance of the inventory from the page.
     */
    Inventory getInventory();

    int getGuiInt();
}
