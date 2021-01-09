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

package com.azortis.azortislib.experimental.inventory.item;

import com.azortis.azortislib.experimental.inventory.serialization.ItemSerializer;
import com.azortis.azortislib.utils.FormatUtil;
import com.azortis.azortislib.xseries.SkullUtils;
import com.azortis.azortislib.xseries.XMaterial;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class Item implements Serializable {
    private static final Gson GSON = new Gson();
    private final ItemStack itemStack;

    @SuppressWarnings("all")
    public Item(XMaterial material) {
        this.itemStack = material.parseItem().orElse(new ItemStack(Material.STONE));
    }

    public Item(ItemStack itemStack) {
        Preconditions.checkArgument(itemStack != null, "ItemStack can't be null!");
        this.itemStack = itemStack;
    }

    public static Item deserialize(JsonObject object) {
        if (object.has("base64")) {
            ItemStack stack = ItemSerializer.itemFrom64(object.get("base64").getAsString());
            return new Item(stack);
        }
        return null;
    }

    public Item name(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(FormatUtil.color(name));
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Item amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public Item lore(String... lore) {
        ItemMeta stackMeta = itemStack.getItemMeta();
        Objects.requireNonNull(stackMeta).setLore(Arrays.asList(FormatUtil.color(lore)));
        itemStack.setItemMeta(stackMeta);
        return this;
    }

    public Item material(Material material) {
        itemStack.setType(material);
        return this;
    }

    @SuppressWarnings("all")
    public Item material(XMaterial material) {
        ItemStack stack = material.parseItem().orElse(new ItemStack(Material.STONE));
        stack.setItemMeta(itemStack.getItemMeta());
        return this;
    }

    public Item lore(List<String> lore) {
        ItemMeta stackMeta = itemStack.getItemMeta();
        assert stackMeta != null;
        stackMeta.setLore(FormatUtil.color(lore));
        itemStack.setItemMeta(stackMeta);
        return this;
    }

    @Deprecated
    public Item dataLegacy(short data) {
        itemStack.setDurability(data);
        return this;
    }

    public Item data(int data) {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta instanceof Damageable)
            ((Damageable) meta).setDamage(data);
        itemStack.setItemMeta(meta);
        return this;
    }

    public Item skull(String identifier) {
        ItemMeta meta = itemStack.getItemMeta();
        if (itemStack.getType() == XMaterial.PLAYER_HEAD.parseMaterial())
            meta = SkullUtils.applySkin(Objects.requireNonNull(meta), identifier);
        itemStack.setItemMeta(meta);
        return this;
    }

    public JsonObject serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("base64", ItemSerializer.itemTo64(itemStack));
        return object;
    }


}
