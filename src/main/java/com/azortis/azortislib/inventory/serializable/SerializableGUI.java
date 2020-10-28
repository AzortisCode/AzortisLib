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
import com.google.common.collect.ImmutableList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

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

    public  <T extends View<T>> GUI toGUI(BiConsumer<InventoryClickEvent, T>[][] itemActions,
                     List<BiConsumer<InventoryCloseEvent, T>> pageActions,
                     List<Function<Page<T>, T>> constructViews) {
        GUI gui = new GUI(name);
        for (int i = 0; i < pages.size(); i++) {
            gui.getPages().add(pages.get(i).toPage(gui, itemActions[i], pageActions.get(i), constructViews.get(i)));
        }
        return gui;
    }
}
