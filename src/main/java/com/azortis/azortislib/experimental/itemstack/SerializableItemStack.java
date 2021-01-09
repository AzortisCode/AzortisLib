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

package com.azortis.azortislib.experimental.itemstack;

import com.azortis.azortislib.experimental.itemstack.meta.SerializableBookMeta;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SerializableItemStack {

    // ItemStack
    private final Material type;
    private final int amount;

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
                itemMeta.getEnchants().forEach((enchantment, level) -> this.enchantments.add(enchantment.getKey().getKey() + ";" + level));
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
        if(enchantments != null) {
            for (String strEnchantment : enchantments){
                NamespacedKey key = NamespacedKey.minecraft(strEnchantment.split(";")[0]);
                itemMeta.addEnchant(Objects.requireNonNull(Enchantment.getByKey(key)), Integer.getInteger(strEnchantment.split(";")[1]), true);
            }
        }
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
