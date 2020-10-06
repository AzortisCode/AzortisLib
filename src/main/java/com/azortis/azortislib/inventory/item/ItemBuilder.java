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

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("all")
public class ItemBuilder {
    private ItemStack item;

    private ItemBuilder(Material material) {
        item = new ItemStack(material);
    }

    private ItemBuilder(ItemStack stack) {
        this.item = stack;
    }

    public static ItemBuilder start(Material material) {
        return new ItemBuilder(material);
    }

    public static ItemBuilder start(ItemStack stack) {
        return new ItemBuilder(stack);
    }

    public ItemBuilder name(@NotNull String name) {
        ItemMeta stackMeta = item.getItemMeta();
        assert stackMeta != null;
        stackMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(stackMeta);
        return this;
    }

    public ItemBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }


    public ItemBuilder lore(String... lore) {
        for (int i = 0; i < lore.length; i++) {
            lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
        }

        ItemMeta stackMeta = item.getItemMeta();
        assert stackMeta != null;
        stackMeta.setLore(Arrays.asList(lore));
        item.setItemMeta(stackMeta);
        return this;
    }


    public ItemBuilder lore(List<String> lore) {
        List<String> list = new ArrayList<>(lore);
        for (int i = 0; i < lore.size(); i++) {
            list.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
        }

        ItemMeta stackMeta = item.getItemMeta();
        assert stackMeta != null;
        stackMeta.setLore(lore);
        item.setItemMeta(stackMeta);
        return this;
    }


    public ItemBuilder data(short data) {
        item.setDurability(data);
        return this;
    }


    public ItemBuilder durability(short durability) {
        item.setDurability(durability);
        return this;
    }

    public ItemStack build() {
        return item;
    }
}
