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

package com.azortis.azortislib.inventory.serializable;

import com.azortis.azortislib.inventory.GUI;
import com.azortis.azortislib.inventory.Page;
import com.azortis.azortislib.inventory.View;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.Serializable;
import java.util.function.BiConsumer;

public class SerializablePage implements Serializable {
    private final int page;
    private final String name;
    private final boolean isGlobal;
    private final SerializableItem[] items;
    private final int pageSize;

    public SerializablePage(int page, String name, boolean isGlobal, SerializableItem[] items, int pageSize) {
        this.page = page;
        this.name = name;
        this.isGlobal = isGlobal;
        this.items = items;
        this.pageSize = pageSize;
    }

    public static SerializablePage fromPage(Page page) {
        SerializableItem[] items = new SerializableItem[page.getItems().length];
        for (int i = 0; i < page.getItems().length; i++) {
            items[i] = SerializableItem.fromItem(page.getItems()[i], i);
        }
        return new SerializablePage(page.getPage(), page.getName(), page.isGlobal(), items, page.getPageSize());
    }

    public int getPage() {
        return page;
    }

    public String getName() {
        return name;
    }

    public boolean isGlobal() {
        return isGlobal;
    }

    public SerializableItem[] getItems() {
        return items;
    }

    public int getPageSize() {
        return pageSize;
    }

    public Page toPage(GUI gui, BiConsumer<InventoryClickEvent, View>[] itemActions, BiConsumer<InventoryCloseEvent, View> closeAction) {
        Page page = new Page(gui, this.page, this.name, this.isGlobal, this.pageSize, closeAction);
        for (int i = 0; i < items.length; i++) {
            page.getItems()[i] = items[i].toItem(page, itemActions[i]);
        }
        return page;
    }
}
