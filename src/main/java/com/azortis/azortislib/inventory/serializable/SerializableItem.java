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

import com.azortis.azortislib.inventory.Page;
import com.azortis.azortislib.inventory.View;
import com.azortis.azortislib.inventory.item.Item;
import com.azortis.azortislib.inventory.item.ItemBuilder;
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
