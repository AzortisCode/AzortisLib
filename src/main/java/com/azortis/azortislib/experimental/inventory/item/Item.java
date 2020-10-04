package com.azortis.azortislib.experimental.inventory.item;

import com.azortis.azortislib.experimental.inventory.View;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public class Item extends ItemStack {
    private String uniqueName;
    private BiConsumer<InventoryClickEvent, View> eventConsumer;

    public Item(ItemStack stack, String uniqueName, BiConsumer<InventoryClickEvent, View> eventConsumer) throws IllegalArgumentException {
        super(stack);
        this.uniqueName = uniqueName;
        this.eventConsumer = eventConsumer;
    }

    public Item(String uniqueName, BiConsumer<InventoryClickEvent, View> eventConsumer) {
        this.uniqueName = uniqueName;
        this.eventConsumer = eventConsumer;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public BiConsumer<InventoryClickEvent, View> getEventConsumer() {
        return eventConsumer;
    }

    public void setEventConsumer(BiConsumer<InventoryClickEvent, View> eventConsumer) {
        this.eventConsumer = eventConsumer;
    }
}
