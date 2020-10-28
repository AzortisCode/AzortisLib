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

import com.azortis.azortislib.inventory.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

// todo: Make an animator of some sort.
@SuppressWarnings({"unchecked", "rawtypes"})
public class GUIManager implements Listener {

    public GUIManager(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null &&
                event.getClickedInventory().getHolder() instanceof View) {
            View view = (View) event.getInventory().getHolder();
            if (view.getPage().getItems()[event.getSlot()] != null) {
                Item item = view.getPage().getItems()[event.getSlot()];
                if (item.getEventConsumer() != null) item.getEventConsumer().accept(event, view);
            }
        }
    }

    @EventHandler
    public void onPlayerClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof View) {
            View view = (View) event.getInventory().getHolder();
            if (view.getPage().getCloseAction() != null)
                view.getPage().getCloseAction().accept(event, view);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerCloseMonitor(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof View) {
            ((View) event.getInventory().getHolder()).getPage()
                    .disposeView((View) event.getInventory().getHolder());
        }
    }
}
