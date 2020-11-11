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

package com.azortis.azortislib.experimental.inventory.serialization;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ItemSerializer {
    public ItemSerializer() {
    }

    public static String itemTo64(ItemStack itemstack) {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            BukkitObjectOutputStream var2 = new BukkitObjectOutputStream(var1);
            var2.writeObject(itemstack);
            var2.close();
            return Base64Coder.encodeLines(var1.toByteArray());
        } catch (Exception var3) {
            throw new IllegalStateException("Unable to save item stack.", var3);
        }
    }

    public static ItemStack itemFrom64(String base) {
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(Base64Coder.decodeLines(base));
            BukkitObjectInputStream objectStream = new BukkitObjectInputStream(byteStream);

            ItemStack itemstack;
            try {
                itemstack = (ItemStack) objectStream.readObject();
            } finally {
                objectStream.close();
            }

            return itemstack;
        } catch (ClassNotFoundException | IOException var8) {
            throw new RuntimeException("Unable to decode class type.", var8);
        }
    }
}
