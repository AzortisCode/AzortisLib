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

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

// todo: document this, ALSO needs to add base64 encoding support...
public class SerializableItem extends Item implements Serializable, Cloneable {
    private String key;
    private String name;
    private List<Integer> slots;
    private List<SerializableItemOptions> options;

    /**
     * Defaults stack size to 1, with no extra data.
     * <p>
     * <b>IMPORTANT: An <i>Item</i>Stack is only designed to contain
     * <i>items</i>. Do not use this class to encapsulate Materials for which
     * {@link Material#isItem()} returns false.</b>
     *
     * @param type item material
     */
    public SerializableItem(@NotNull Material type) {
        super(type);
    }

    public List<SerializableItemOptions> getOptions() {
        return options;
    }

    public SerializableItem setOptions(List<SerializableItemOptions> options) {
        this.options = options;
        return this;
    }

    public String getKey() {
        return key;
    }

    public SerializableItem setKey(String key) {
        this.key = key;
        return this;
    }

    public String getName() {
        return name;
    }

    public SerializableItem setName(String name) {
        this.name = name;
        return this;
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public SerializableItem setSlots(List<Integer> slots) {
        this.slots = slots;
        return this;
    }

    @Override
    public @NotNull SerializableItem clone() {
        SerializableItem item = (SerializableItem) super.clone();
        item.key = this.key;
        item.name = this.name;
        item.slots = this.slots;
        return item;
    }

}


