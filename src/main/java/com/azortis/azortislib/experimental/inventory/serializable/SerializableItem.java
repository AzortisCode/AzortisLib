package com.azortis.azortislib.experimental.inventory.serializable;

import com.azortis.azortislib.experimental.inventory.Page;
import com.azortis.azortislib.experimental.inventory.View;
import com.azortis.azortislib.experimental.inventory.item.Item;
import com.azortis.azortislib.experimental.inventory.item.ItemBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

// todo: add nbt support, XMaterial support
public class SerializableItem implements Serializable {
    private final String uniqueName;
    private final int slot;
    private final ImmutableMap<String, Integer> enchantments;
    private final String name;
    private final ImmutableList<String> lore;
    private final int amount;
    // Is a string for both ID and/or material support
    private final String material;
    // Data support for older versions. Is useless on newer versions and should be disabled depending on version.
    private final int data;

    public SerializableItem(String uniqueName, int slot, ImmutableMap<String, Integer> enchantments, String name, ImmutableList<String> lore, int amount, String material, int data) {
        this.uniqueName = uniqueName;
        this.slot = slot;
        this.enchantments = enchantments;
        this.name = name;
        this.lore = lore;
        this.amount = amount;
        this.material = material;
        this.data = data;
    }

    public static SerializableItem fromItem(Item item, int slot) {
        Map<String, Integer> enchants = new HashMap<>();
        for (Map.Entry<Enchantment, Integer> enchantmentIntegerEntry : item.getEnchantments().entrySet()) {
            enchants.put(enchantmentIntegerEntry.getKey().getName(), enchantmentIntegerEntry.getValue());
        }
        return new SerializableItem(item.getUniqueName(), slot, ImmutableMap.copyOf(enchants), item.getItemMeta().getDisplayName(),
                ImmutableList.copyOf(item.getItemMeta().getLore()), item.getAmount(), item.getType().toString(), item.getDurability());
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public int getSlot() {
        return slot;
    }

    public ImmutableMap<String, Integer> getEnchantments() {
        return enchantments;
    }

    public String getName() {
        return name;
    }

    public ImmutableList<String> getLore() {
        return lore;
    }

    public int getAmount() {
        return amount;
    }

    public String getMaterial() {
        return material;
    }

    public int getData() {
        return data;
    }

    public Item toItem(Page page, BiConsumer<InventoryClickEvent, View> action) {
        ItemBuilder builder = ItemBuilder.start(Material.getMaterial(material))
                .amount(amount)
                .data((short) data)
                .lore(lore)
                .name(name);
        Item item = new Item(builder.build(), name, action);
        Map<Enchantment, Integer> enchantmentIntegerMap = new HashMap<>();
        for (Map.Entry<String, Integer> stringIntegerEntry : enchantments.entrySet()) {
            enchantmentIntegerMap.put(Enchantment.getByName(stringIntegerEntry.getKey()), stringIntegerEntry.getValue());
        }
        item.addUnsafeEnchantments(enchantmentIntegerMap);
        page.getItems()[slot] = item;
        return item;
    }
}
