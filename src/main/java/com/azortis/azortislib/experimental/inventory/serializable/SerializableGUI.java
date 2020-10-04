package com.azortis.azortislib.experimental.inventory.serializable;

import com.azortis.azortislib.experimental.inventory.GUI;
import com.azortis.azortislib.experimental.inventory.Page;
import com.azortis.azortislib.experimental.inventory.View;
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
