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

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public final class Item implements Cloneable {
    private final ItemStack itemStack;
    private final Consumer<InventoryClickEvent> action;
    private boolean isAir = false;

    public Item(@NotNull ItemStack itemStack, @Nullable Consumer<InventoryClickEvent> action) {
        this.itemStack = itemStack;
        this.action = action;
    }

    public Item(boolean isAir) {
        this.isAir = isAir;
        itemStack = null;
        action = null;
    }

    @Override
    public Item clone() {
        // We do not call super.clone as that would only make a shallow copy for the itemstack :(.
        return new Item(this.itemStack.clone(), action);
    }

    @Nullable
    public ItemStack getItemStack() {
        return itemStack;
    }

    @Nullable
    public Consumer<InventoryClickEvent> getAction() {
        return action;
    }
}
