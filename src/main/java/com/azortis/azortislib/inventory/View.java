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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class View implements InventoryHolder {
    private Inventory inventory;
    private Page page;

    public View(Page page) {
        this.page = page;
        inventory = Bukkit.createInventory(this, page.getPageSize(),
                ChatColor.translateAlternateColorCodes('&', page.getName()));
        inventory.setContents(page.getItems());
    }

    public Page getPage() {
        return page;
    }

    public void dispose() {
        page = null;
        inventory = null;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
