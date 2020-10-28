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

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class GUIBuilder {
    private final GUI gui;

    public GUIBuilder(String uniqueName) {
        gui = new GUI(uniqueName);
    }

    public GUI getGui() {
        return gui;
    }

    public <T extends View<T>> PageBuilder<T> addPage(int pageSize) {
        return new PageBuilder<T>(this, pageSize);
    }

    public static class PageBuilder<T extends View<T>> {
        private final GUIBuilder guiBuilder;
        public int page;
        public String name;
        public boolean isGlobal;
        public BiConsumer<InventoryCloseEvent, T> closeAction;
        public Function<Page<T>, T> constructView;
        public Item[] items;
        private int pageSize;

        public PageBuilder(GUIBuilder builder, int pageSize) {
            this.guiBuilder = builder;
            this.pageSize = pageSize;
            items = new Item[pageSize];
        }

        public void resizePage(int pageSize) {
            this.pageSize = pageSize;
            Item[] temp = items;
            items = new Item[pageSize];
            System.arraycopy(temp, 0, items, 0, temp.length);
        }


        public GUIBuilder with(Consumer<PageBuilder<T>> pageBuilderConsumer) {
            pageBuilderConsumer.accept(this);
            Page<T> p = new Page<>(guiBuilder.gui, page, name, isGlobal, pageSize, closeAction, constructView);
            p.setItems(items);
            guiBuilder.gui.getPages().add(p);
            return guiBuilder;
        }
    }
}
