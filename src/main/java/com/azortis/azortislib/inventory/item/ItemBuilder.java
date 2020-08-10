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

package com.azortis.azortislib.inventory.item;

import com.azortis.azortislib.inventory.exceptions.InvalidItemException;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

// todo: document this
@SuppressWarnings("all")
public class ItemBuilder<T extends Item> {
    private T item;

    // This is only called when the item is most definitely of type Item, this saves performance by not having to deal with reflection.
    private ItemBuilder(Material material) {
        item = (T) new Item(material);
    }

    public ItemBuilder(Material material, Class<T> type) {
        Constructor<T> constructor;
        try {
            constructor = type.getConstructor(Material.class);
        } catch (NoSuchMethodException e) {
            throw new InvalidItemException("This item does not have the constructor with " + type + "(org.bukkit.Material)");
        }
        try {
            item = constructor.newInstance(material);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static ItemBuilder<Item> start(Material material) {
        return new ItemBuilder<>(material);
    }

    public static <T extends Item> ItemBuilder<T> start(Material material, Class<T> type) {
        return new ItemBuilder<>(material, type);
    }

    public void setConsumer(Consumer<InventoryClickEvent> consumer) {
        item.setAction(consumer);
    }

    public ItemBuilder<T> name(@NotNull String name) {
        ItemMeta stackMeta = item.getItemMeta();
        assert stackMeta != null;
        stackMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(stackMeta);
        return this;
    }

    public ItemBuilder<T> amount(int amount) {
        item.setAmount(amount);
        return this;
    }


    public ItemBuilder<T> lore(String... lore) {
        for (int i = 0; i < lore.length; i++) {
            lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
        }

        ItemMeta stackMeta = item.getItemMeta();
        assert stackMeta != null;
        stackMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(stackMeta);
        return this;
    }


    public ItemBuilder<T> lore(List<String> lore) {
        for (int i = 0; i < lore.size(); i++) {
            lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }

        ItemMeta stackMeta = item.getItemMeta();
        assert stackMeta != null;
        stackMeta.setLore(lore);
        item.setItemMeta(stackMeta);
        return this;
    }


    public ItemBuilder<T> data(short data) {
        item.setDurability(data);
        return this;
    }


    public ItemBuilder<T> durability(short durability) {
        item.setDurability(durability);
        return this;
    }

    public T build() {
        return item;
    }
}
