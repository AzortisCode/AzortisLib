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
import java.util.function.Function;

public class Page<T extends View<T>> {
    private final GUI gui;
    private final int page;
    private final String name;
    private final boolean isGlobal;
    private final Item[] items;
    private final int pageSize;
    private final Set<T> views;
    private Function<Page<T>, T> constructView;
    private BiConsumer<InventoryCloseEvent, T> closeAction;
    public Page(GUI gui, int page, String name, boolean isGlobal, int pageSize, BiConsumer<InventoryCloseEvent, T> closeAction,
                Function<Page<T>, T> constructView) {
        this.gui = gui;
        this.page = page;
        this.name = name;
        this.isGlobal = isGlobal;
        this.items = new Item[pageSize];
        this.pageSize = pageSize;
        this.closeAction = closeAction;
        this.constructView = constructView;
        views = new HashSet<>();
    }

    public void setConstructView(Function<Page<T>, T> constructView) {
        this.constructView = constructView;
    }

    public BiConsumer<InventoryCloseEvent, T> getCloseAction() {
        return closeAction;
    }

    public void setCloseAction(BiConsumer<InventoryCloseEvent, T> closeAction) {
        this.closeAction = closeAction;
    }

    public void disposeView(T view) {
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

    public Set<T> getViews() {
        return views;
    }

    public int getPageSize() {
        return pageSize;
    }

    public T getView() {
        if (isGlobal) {
            if (views.size() >= 1) {
                return (T) views.iterator().next();
            }
        }
        T view = constructView.apply(this);
        views.add(view);
        return view;
    }
}
