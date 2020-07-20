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

package com.azortis.azortislib.experimental.inventory.item;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class Item extends ItemStack implements Cloneable {
    protected transient Consumer<InventoryClickEvent> action = event -> event.setCancelled(true);

    /**
     * Defaults stack size to 1, with no extra data.
     * <p>
     * <b>IMPORTANT: An <i>Item</i>Stack is only designed to contain
     * <i>items</i>. Do not use this class to encapsulate Materials for which
     * {@link Material#isItem()} returns false.</b>
     *
     * @param type   item material
     * @param action {@link Consumer<InventoryClickEvent>} the action that will be played when the item is clicked.
     */
    public Item(@NotNull Material type, @NotNull Consumer<InventoryClickEvent> action) {
        super(type);
        this.action = action;
    }

    /**
     * An item stack with no extra data.
     * <p>
     * <b>IMPORTANT: An <i>Item</i>Stack is only designed to contain
     * <i>items</i>. Do not use this class to encapsulate Materials for which
     * {@link Material#isItem()} returns false.</b>
     *
     * @param type   item material
     * @param amount stack size
     * @param action {@link Consumer<InventoryClickEvent>} the action that will be played when the item is clicked.
     */
    public Item(@NotNull Material type, int amount, @NotNull Consumer<InventoryClickEvent> action) {
        super(type, amount);
        this.action = action;
    }

    /**
     * Defaults stack size to 1, with no extra data.
     * <p>
     * <b>IMPORTANT: An <i>Item</i>Stack is only designed to contain
     * <i>items</i>. Do not use this class to encapsulate Materials for which
     * {@link Material#isItem()} returns false.</b>
     *
     * @param type item material
     */
    public Item(@NotNull Material type) {
        super(type);
    }

    /**
     * An item stack with no extra data.
     * <p>
     * <b>IMPORTANT: An <i>Item</i>Stack is only designed to contain
     * <i>items</i>. Do not use this class to encapsulate Materials for which
     * {@link Material#isItem()} returns false.</b>
     *
     * @param type   item material
     * @param amount stack size
     */
    public Item(@NotNull Material type, int amount) {
        super(type, amount);
    }

    /**
     * An item stack with the specified damage / durability
     *
     * @param type   item material
     * @param amount stack size
     * @param damage durability / damage
     * @deprecated see {@link #setDurability(short)}
     */
    public Item(@NotNull Material type, int amount, short damage) {
        super(type, amount, damage);
    }

    /**
     * @param type   the type
     * @param amount the amount in the stack
     * @param damage the damage value of the item
     * @param data   the data value or null
     * @deprecated this method uses an ambiguous data byte object
     */
    public Item(@NotNull Material type, int amount, short damage, @Nullable Byte data) {
        super(type, amount, damage, data);
    }

    /**
     * Creates a new item stack derived from the specified stack
     *
     * @param stack the stack to copy
     * @throws IllegalArgumentException if the specified stack is null or
     *                                  returns an item meta not created by the item factory
     */
    public Item(@NotNull ItemStack stack) throws IllegalArgumentException {
        super(stack);
    }

    /**
     * An item stack with the specified damage / durability
     *
     * @param type   item material
     * @param amount stack size
     * @param damage durability / damage
     * @param action {@link Consumer<InventoryClickEvent>} the action that will be played when the item is clicked.
     * @deprecated see {@link #setDurability(short)}
     */
    public Item(@NotNull Material type, int amount, short damage, @NotNull Consumer<InventoryClickEvent> action) {
        super(type, amount, damage);
        this.action = action;
    }

    /**
     * @param type   the type
     * @param amount the amount in the stack
     * @param damage the damage value of the item
     * @param data   the data value or null
     * @param action {@link Consumer<InventoryClickEvent>} the action that will be played when the item is clicked.
     * @deprecated this method uses an ambiguous data byte object
     */
    public Item(@NotNull Material type, int amount, short damage, @Nullable Byte data, @NotNull Consumer<InventoryClickEvent> action) {
        super(type, amount, damage, data);
        this.action = action;
    }

    /**
     * Creates a new item stack derived from the specified stack
     *
     * @param action {@link Consumer<InventoryClickEvent>} the action that will be played when the item is clicked.
     * @param stack  the stack to copy
     * @throws IllegalArgumentException if the specified stack is null or
     *                                  returns an item meta not created by the item factory
     */
    public Item(@NotNull ItemStack stack, @NotNull Consumer<InventoryClickEvent> action) throws IllegalArgumentException {
        super(stack);
        this.action = action;
    }

    /**
     * Gets the action which should be called when the item is clicked.
     *
     * @return {@link Consumer<InventoryClickEvent>} the action to consume
     */
    @NotNull
    public Consumer<InventoryClickEvent> getAction() {
        return action;
    }

    /**
     * Sets the action of the item when clicked.
     *
     * @param action {@link Consumer<InventoryClickEvent>} the action
     */
    public void setAction(@NotNull Consumer<InventoryClickEvent> action) {
        this.action = action;
    }

    @Override
    public @NotNull Item clone() {
        Item item = (Item) super.clone();
        item.action = this.action;
        return item;
    }

}
