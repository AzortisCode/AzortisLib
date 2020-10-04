package com.azortis.azortislib.experimental.inventory;

import com.azortis.azortislib.experimental.inventory.item.Item;
import com.google.common.collect.ImmutableSet;
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
        return ImmutableSet.copyOf(views);
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
