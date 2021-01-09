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

package com.azortis.azortislib.inventory.item;

import com.azortis.azortislib.inventory.View;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public class Item extends ItemStack {
    private String uniqueName;
    private BiConsumer<InventoryClickEvent, View> eventConsumer;

    public Item(ItemStack stack, String uniqueName, BiConsumer<InventoryClickEvent, View> eventConsumer) throws IllegalArgumentException {
        super(stack);
        this.uniqueName = uniqueName;
        this.eventConsumer = eventConsumer;
    }

    public Item(String uniqueName, BiConsumer<InventoryClickEvent, View> eventConsumer) {
        this.uniqueName = uniqueName;
        this.eventConsumer = eventConsumer;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public BiConsumer<InventoryClickEvent, View> getEventConsumer() {
        return eventConsumer;
    }

    public void setEventConsumer(BiConsumer<InventoryClickEvent, View> eventConsumer) {
        this.eventConsumer = eventConsumer;
    }
}
