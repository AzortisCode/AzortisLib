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

import com.azortis.azortislib.experimental.inventory.inventories.IInventory;
import com.azortis.azortislib.experimental.inventory.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.plugin.java.JavaPlugin;

// todo: document this
public class InventoryListener implements Listener {
    public static void init(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), plugin);
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof IInventory
                && event.getCurrentItem() != null) {
            Item i = ((IInventory) event.getClickedInventory().getHolder()).getItem(event.getSlot());
            if (i != null) i.getAction().accept(event);
        }


    }

    @EventHandler
    public void onPlayerOpen(InventoryOpenEvent event) {
        if (event.getInventory().getHolder() instanceof IInventory)
            ((IInventory) event.getInventory().getHolder()).getOpenConsumers().forEach(consumer -> consumer.accept(event));
    }

    @EventHandler
    public void onPlayerClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof IInventory)
            ((IInventory) event.getInventory().getHolder()).getCloseConsumers().forEach(consumer -> consumer.accept(event));
    }
}
