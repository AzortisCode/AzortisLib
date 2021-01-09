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

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class GUIBuilder {
    private final GUI gui;

    public GUIBuilder(String uniqueName) {
        gui = new GUI(uniqueName);
    }

    public GUI getGui() {
        return gui;
    }

    public PageBuilder addPage(int pageSize) {
        return new PageBuilder(this, pageSize);
    }

    public static class PageBuilder {
        private final GUIBuilder guiBuilder;
        public int page;
        public String name;
        public boolean isGlobal;
        public BiConsumer<InventoryCloseEvent, View> closeAction;
        public Item[] items;
        private int pageSize;

        private PageBuilder(GUIBuilder builder, int pageSize) {
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


        public GUIBuilder with(Consumer<PageBuilder> pageBuilderConsumer) {
            pageBuilderConsumer.accept(this);
            Page p = new Page(guiBuilder.gui, page, name, isGlobal, pageSize, closeAction);
            p.setItems(items);
            guiBuilder.gui.getPages().add(p);
            return guiBuilder;
        }
    }
}
