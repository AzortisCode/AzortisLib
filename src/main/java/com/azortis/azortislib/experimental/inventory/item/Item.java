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
