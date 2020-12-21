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

package com.azortis.azortislib.experimental.itemstack;

import com.azortis.azortislib.experimental.itemstack.meta.SerializableBookMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SerializableItemStack {

    // ItemStack
    private Material type;
    private int amount;

    // ItemMeta
    private String displayName;
    private List<String> lore;
    private List<String> enchantments;
    private int customModelData;
    private boolean unbreakable;

    private List<ItemFlag> itemFlags;
    private List<String> attributes;

    // Item Specific Meta
    private SerializableBookMeta bookMeta;

    public SerializableItemStack(ItemStack itemStack){
        this.type = itemStack.getType();
        this.amount = itemStack.getAmount();
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            if(itemMeta.hasDisplayName()) this.displayName = itemMeta.getDisplayName();
            if(itemMeta.hasLore()) this.lore = itemMeta.getLore();
            if(itemMeta.hasEnchants()) {
                this.enchantments = new ArrayList<>();
                itemMeta.getEnchants().forEach((enchantment, level) -> this.enchantments.add(enchantment.toString() + ";" + level));
            }
            if(itemMeta.hasCustomModelData())this.customModelData = itemMeta.getCustomModelData();
            this.unbreakable = itemMeta.isUnbreakable();
            if(itemMeta.getItemFlags().size() > 0){
                this.itemFlags = new ArrayList<>(itemMeta.getItemFlags());
            }
            switch (type){
                case BOOK:
                    bookMeta = new SerializableBookMeta((BookMeta) itemMeta);
            }
        }
    }

    public ItemStack toItemStack(){
        ItemStack itemStack = new ItemStack(type, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        if(displayName != null) itemMeta.setDisplayName(displayName);
        if(lore != null) itemMeta.setLore(lore);
        itemMeta.setCustomModelData(customModelData);
        itemMeta.setUnbreakable(unbreakable);

        // Item specific meta's
        switch (type){
            case BOOK:
                if(bookMeta != null)bookMeta.applyMeta((BookMeta) itemMeta);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
