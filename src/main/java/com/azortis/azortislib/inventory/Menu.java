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

package com.azortis.azortislib.inventory;


import com.azortis.azortislib.inventory.item.InventoryItem;
import com.azortis.azortislib.inventory.item.action.InventoryAction;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public interface Menu extends InventoryHolder {
    InventoryAction getOpenAction();

    InventoryAction getCloseAction();

    String getTitle();

    Inventory getInventory();

    InventoryItem[] getItems();

    default void openInventory(Player player) {
        new Window(player, this);
    }

    void addItem(InventoryItem item, int slot);

    void removeItem(int slot);
}