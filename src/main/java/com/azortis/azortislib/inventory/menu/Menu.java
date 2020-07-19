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

package com.azortis.azortislib.inventory.menu;

import com.azortis.azortislib.inventory.IMenu;
import com.azortis.azortislib.inventory.object.Action;
import com.azortis.azortislib.inventory.object.Condition;
import com.azortis.azortislib.inventory.object.InventoryItem;
import com.azortis.azortislib.inventory.object.InventoryLayout;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Menu implements IMenu {
    private final InventoryItem[] items;
    private final List<Condition<InventoryOpenEvent>> openConditions;
    private final List<Condition<InventoryCloseEvent>> closeConditions;
    private String name;

    public Menu(int rows, String name) {
        items = new InventoryItem[rows * 9];
        this.name = name;
        openConditions = new ArrayList<>();
        closeConditions = new ArrayList<>();
    }

    @Override
    public void construct(Action<IMenu> action) {
        action.action(this);
    }

    @Override
    public InventoryItem getItem(int i) {
        return items[i];
    }

    @Override
    public void setItem(int i, InventoryItem inventoryItem) {
        items[i] = inventoryItem;
    }

    @Override
    public void addItem(InventoryItem inventoryItem) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = inventoryItem;
                break;
            }
        }
    }

    @Override
    public void setLayout(InventoryLayout inventoryLayout) {
        for (int i = 0; i < inventoryLayout.getLayout().length && i < items.length; i++) {
            if (inventoryLayout.getLayout()[i] != null) items[i] = inventoryLayout.getLayout()[i];
        }
    }

    @Override
    public void addOpenCondition(Condition<InventoryOpenEvent> condition) {
        openConditions.add(condition);
    }

    @Override
    public void addCloseCondition(Condition<InventoryCloseEvent> condition) {
        closeConditions.add(condition);
    }

    @Override
    public List<Condition<InventoryOpenEvent>> getOpenCondition() {
        return openConditions;
    }

    @Override
    public List<Condition<InventoryCloseEvent>> getCloseCondition() {
        return closeConditions;
    }

    @Override
    public int getInventorySize() {
        return items.length;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public void setTitle(String s) {
        name = s;
    }

    @Override
    public @NotNull Inventory getInventory() {
        Inventory i = Bukkit.createInventory(this, items.length, ChatColor.translateAlternateColorCodes('&', name));
        i.setStorageContents(items);
        return i;
    }
}
