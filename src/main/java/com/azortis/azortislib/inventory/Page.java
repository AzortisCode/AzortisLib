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
