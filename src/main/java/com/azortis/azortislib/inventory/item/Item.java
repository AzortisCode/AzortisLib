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
