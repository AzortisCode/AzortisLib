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
