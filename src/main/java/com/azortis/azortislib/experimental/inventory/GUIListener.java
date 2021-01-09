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

import com.azortis.azortislib.experimental.inventory.item.ItemClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class GUIListener implements Listener {
    public GUIListener(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerClose(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof View) {
            View view = (View) event.getInventory().getHolder();
            if (view.getPage().closeAction() != null)
                view.getPage().closeAction().accept(event, view);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerCloseMonitor(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof View) {
            View view = (View) event.getInventory().getHolder();
            if (!view.getPage().isGlobal()) {
                view.dispose();
                view.getPage().disposeView(view);
            }
        }
    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof View) {
            View view = (View) event.getInventory().getHolder();
            ItemClickEvent itemClickEvent = new ItemClickEvent(event, view);
            view.getPage().getButton(event.getSlot()).getClickAction().forEach(action -> action.accept(itemClickEvent));
        }
    }

}
