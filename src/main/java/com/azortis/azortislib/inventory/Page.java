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
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class Page {
    private final GUI gui;
    private final int page;
    private final String name;
    private final boolean isGlobal;
    private final Item[] items;
    private final int pageSize;
    private final Set<View> views;
    private BiConsumer<InventoryCloseEvent, View> closeAction;

    public Page(GUI gui, int page, String name, boolean isGlobal, int pageSize, BiConsumer<InventoryCloseEvent, View> closeAction) {
        this.gui = gui;
        this.page = page;
        this.name = name;
        this.isGlobal = isGlobal;
        this.items = new Item[pageSize];
        this.pageSize = pageSize;
        this.closeAction = closeAction;
        views = new HashSet<>();
    }

    public BiConsumer<InventoryCloseEvent, View> getCloseAction() {
        return closeAction;
    }

    public void setCloseAction(BiConsumer<InventoryCloseEvent, View> closeAction) {
        this.closeAction = closeAction;
    }

    public void disposeView(View view) {
        if (!isGlobal) {
            views.removeIf(view1 -> view1 == view);
            view.dispose();
        }
    }

    public GUI getGui() {
        return gui;
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

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        System.arraycopy(items, 0, this.items, 0, this.items.length);
    }

    public Set<View> getViews() {
        return views;
    }

    public int getPageSize() {
        return pageSize;
    }

    public View getView() {
        if (isGlobal) {
            if (views.size() >= 1) {
                return views.iterator().next();
            }
        }
        View view = new View(this);
        views.add(view);
        return view;
    }
}
