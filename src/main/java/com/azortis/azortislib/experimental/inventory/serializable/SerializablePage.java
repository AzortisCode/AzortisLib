package com.azortis.azortislib.experimental.inventory.serializable;

import com.azortis.azortislib.experimental.inventory.GUI;
import com.azortis.azortislib.experimental.inventory.Page;
import com.azortis.azortislib.experimental.inventory.View;
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
