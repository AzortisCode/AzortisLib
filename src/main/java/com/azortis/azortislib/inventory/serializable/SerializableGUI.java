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
import com.google.common.collect.ImmutableList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class SerializableGUI implements Serializable {
    private final ImmutableList<SerializablePage> pages;
    private final String name;

    public SerializableGUI(ImmutableList<SerializablePage> pages, String name) {
        this.pages = pages;
        this.name = name;
    }

    public static SerializableGUI fromGUI(GUI gui) {
        List<SerializablePage> pages = new ArrayList<>();
        for (Page page : gui.getPages()) {
            pages.add(SerializablePage.fromPage(page));
        }
        return new SerializableGUI(ImmutableList.copyOf(pages), gui.getName());
    }

    public ImmutableList<SerializablePage> getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }

    public GUI toGUI(BiConsumer<InventoryClickEvent, View>[][] itemActions,
                     List<BiConsumer<InventoryCloseEvent, View>> pageActions) {
        GUI gui = new GUI(name);
        for (int i = 0; i < pages.size(); i++) {
            gui.getPages().add(pages.get(i).toPage(gui, itemActions[i], pageActions.get(i)));
        }
        return gui;
    }
}
