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
