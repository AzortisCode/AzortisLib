package com.azortis.azortislib.experimental.inventory;

import com.azortis.azortislib.experimental.inventory.item.Item;
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
            guiBuilder.gui.getPages().add(p);
            return guiBuilder;
        }
    }
}
